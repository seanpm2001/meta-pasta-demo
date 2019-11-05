SUMMARY = "v4l2loopback - a kernel module to create V4L2 loopback devices"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit module

SRC_URI = "git://github.com/umlaeute/v4l2loopback.git;protocol=https"
SRCREV = "d3b198ef6f57ca512fb25147c9d85b922fd4651a"

PACKAGES += " ${PN}-utils ${PN}-examples "

S = "${WORKDIR}/git"

# https://github.com/umlaeute/v4l2loopback/blob/master/Makefile
# The Makefile makes use of some variables
do_compile () {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    oe_runmake KERNEL_PATH=${STAGING_KERNEL_DIR}   \
                KERNEL_VERSION=${KERNEL_VERSION}    \
                CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
                AR="${KERNEL_AR}" \
                O=${STAGING_KERNEL_BUILDDIR} \
                KBUILD_EXTRA_SYMBOLS="${KBUILD_EXTRA_SYMBOLS}" \
                KERNELRELEASE="${KERNEL_VERSION}" \
                KERNEL_DIR="${STAGING_KERNEL_BUILDDIR}" \
                PREFIX="${S}" \
                ${MAKE_TARGETS}
}

do_compile_append () {
    # Build utils before unset flags
    # We cannot rely on Makefile
    cd examples
    #oe_runmake 'CC=${CC}' 'LD=${LD}' 'LDFLAGS=${LDFLAGS}' 'CFLAGS=${CFLAGS}' all
    ${CC} ${CFLAGS} ${LDFLAGS} -o ondemandcam ondemandcam.c -lrt -lpthread
    ${CC} ${CFLAGS} ${LDFLAGS} -o yuv4mpeg_to_v4l2 yuv4mpeg_to_v4l2.c
    ${CC} ${CFLAGS} ${LDFLAGS} -o test test.c
    cd ..
}

do_install () {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    oe_runmake DEPMOD=echo MODLIB="${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}" \
                INSTALL_FW_PATH="${D}${nonarch_base_libdir}/firmware" \
                CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
                O=${STAGING_KERNEL_BUILDDIR} \
                KERNELRELEASE="${KERNEL_VERSION}" \
                KERNEL_DIR="${STAGING_KERNEL_BUILDDIR}" \
                PREFIX="${D}/${bindir}/.." \
                install install-utils

    if [ ! -e "${B}/${MODULES_MODULE_SYMVERS_LOCATION}/Module.symvers" ] ; then
        bbwarn "Module.symvers not found in ${B}/${MODULES_MODULE_SYMVERS_LOCATION}"
        bbwarn "Please consider setting MODULES_MODULE_SYMVERS_LOCATION to a"
        bbwarn "directory below B to get correct inter-module dependencies"
    else
        install -Dm0644 "${B}/${MODULES_MODULE_SYMVERS_LOCATION}"/Module.symvers ${D}${includedir}/${BPN}/Module.symvers
        # Module.symvers contains absolute path to the build directory.
        # While it doesn't actually seem to matter which path is specified,
        # clear them out to avoid confusion
        sed -e 's:${B}/::g' -i ${D}${includedir}/${BPN}/Module.symvers
    fi
}

do_install_append () {
    install -d ${D}/${bindir}
    install -m 0755 ${S}/examples/test ${D}/${bindir}/${PN}-test
    install -m 0755 ${S}/examples/ondemandcam ${D}/${bindir}
    install -m 0755 ${S}/examples/yuv4mpeg_to_v4l2 ${D}/${bindir}
}

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-${PN}"

FILES_${PN}-utils += "${bindir}/${PN}-ctl"
FILES_${PN}-examples += " \
    ${bindir}/${PN}-test \
    ${bindir}/ondemandcam \
    ${bindir}/yuv4mpeg_to_v4l2 \
"

KERNEL_MODULE_AUTOLOAD = "v4l2loopback"
KERNEL_MODULE_PROBECONF = "v4l2loopback"
module_conf_v4l2loopback = "options v4l2loopback video_nr=14 exclusive_caps=1"
