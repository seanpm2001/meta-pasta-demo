DESCRIPTION = "Lightweight, Portable, Flexible Distributed/Mobile Deep \
Learning with Dynamic, Mutation-aware Dataflow Dep Scheduler; for Python, R, \
Julia, Scala, Go, Javascript and more"
SUMMARY = "Apache MXNet (incubating) for Deep Learning"
HOMEPAGE = "https://mxnet.apache.org/"
SECTION = "libs"
LICENSE = "Apache-2.0"

DEPENDS = " openblas lapack opencv ninja-native "

LIC_FILES_CHKSUM = "file://LICENSE;md5=bea76d64fe6f7e778c44003c8bdd7bdb"

SRC_URI = " gitsm://github.com/apache/incubator-mxnet.git;protocol=https;branch=v1.5.x "
# Release 1.5.0
SRCREV = "75a9e187d00a8b7ebc71412a02ed0e3ae489d91f"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE = "   -DUSE_BLAS=open \
                    -DUSE_CUDA=0 \
                    -DUSE_CUDNN=0 \
                    -DENABLE_CUDA_RTC=0 \
                    -DUSE_JEMALLOC=0 \
                    -DUSE_MKL=0 \
                    -DUSE_MKLDNN=0 \
                    -DUSE_MKL_IF_AVAILABLE=0 \
                    -GNinja "

#-DCFLAGS="${TARGET_CFLAGS}" 
#-DINCLUDE_DIRECTORIES="${TARGET_CFLAGS}" 
#-DCMAKE_CXX_FLAGS="${TARGET_CFLAGS}" 
#-DCMAKE_C_FLAGS="${TARGET_CFLAGS}" 

#PACKAGECONFIG ?= "ostree ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)} ${@bb.utils.filter('SOTA_CLIENT_FEATURES', 'hsm serialcan ubootenv', d)}"
#PACKAGECONFIG_class-native = "sota-tools"
#PACKAGECONFIG[warning-as-error] = "-DWARNING_AS_ERROR=ON,-DWARNING_AS_ERROR=OFF,"
#PACKAGECONFIG[ostree] = "-DBUILD_OSTREE=ON,-DBUILD_OSTREE=OFF,ostree,"
#PACKAGECONFIG[hsm] = "-DBUILD_P11=ON,-DBUILD_P11=OFF,libp11,"
#PACKAGECONFIG[sota-tools] = "-DBUILD_SOTA_TOOLS=ON ${GARAGE_SIGN_OPS},-DBUILD_SOTA_TOOLS=OFF,glib-2.0,"
#PACKAGECONFIG[systemd] = "-DBUILD_SYSTEMD=ON,-DBUILD_SYSTEMD=OFF,systemd,"
#PACKAGECONFIG[load-tests] = "-DBUILD_LOAD_TESTS=ON,-DBUILD_LOAD_TESTS=OFF,"
#PACKAGECONFIG[serialcan] = ",,,slcand-start"
#PACKAGECONFIG[ubootenv] = ",,,u-boot-fw-utils aktualizr-uboot-env-rollback"

do_compile_prepend() {
    EXTRA_INCLUDES="-I${WORKDIR}/recipe-sysroot-native/usr/lib/aarch64-tdx-linux/gcc/aarch64-tdx-linux/9.2.0/include \
        -I${WORKDIR}/recipe-sysroot-native/usr/lib/aarch64-tdx-linux/gcc/aarch64-tdx-linux/9.2.0/include-fixed \
        -I${WORKDIR}/recipe-sysroot/usr/include/c++/9.2.0 \
        -I${WORKDIR}/recipe-sysroot/usr/include/c++/9.2.0/aarch64-tdx-linux \
        -I${WORKDIR}/recipe-sysroot/usr/include/c++/9.2.0/backward \
        -I${WORKDIR}/recipe-sysroot/usr/lib/aarch64-tdx-linux/9.2.0/include \
        -I${WORKDIR}/recipe-sysroot/usr/include"
    sed '/^\ \ INCLUDES/ s@$@\ '"${EXTRA_INCLUDES}"'@' ${B}/build.ninja > ${B}/custom-build.ninja
}

do_compile() {
	ninja -v ${PARALLEL_MAKE} -f ${B}/custom-build.ninja
}

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${includedir}
    DESTDIR=${D} ninja -v ${PARALLEL_MAKE} -f ${B}/custom-build.ninja install
}

do_install_append() {
    mv ${D}${libdir}/lib${PN}.so ${D}${libdir}/lib${PN}.so.${PV}
    cd ${D}${libdir}
    ln -s lib${PN}.so.${PV} lib${PN}.so
}

FILES_${PN} += " \
    ${libdir}/libmxnet.so \
"

FILES_${PN}-dev += " \
    ${includedir}/* \
    ${libdir}/cmake \
"

FILES_${PN}-staticdev += "\
    ${libdir}/*.a \
"

#INSANE_SKIP_${PN} += "dev-so"