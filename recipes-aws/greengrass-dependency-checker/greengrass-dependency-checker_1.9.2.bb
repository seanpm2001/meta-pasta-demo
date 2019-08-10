SUMMARY = "Greengrass core dependencies checker"
DESCRIPTION = "The script 'check\_ggc\_dependencies' verifies if the host \
device has all the dependencies required to run Greengrass core"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b609ee6efba4cb2a7bd8205d277a3b5b"

S = "${WORKDIR}/git"
SRCREV = "4928c0b0a0a630a125fa8c70c9d76eaa8df61450"
BRANCH = "master"
SRC_URI = "git://github.com/aws-samples/aws-greengrass-samples.git;protocol=https;branch=${BRANCH}"

GDCDIR = "greengrass-dependency-checker-GGCv1.9.x"
do_install () {
	install -d ${D}${bindir}
	install -m 0775 ${S}/${GDCDIR}/check_ggc_dependencies ${D}${bindir}

    install -m 0664 ${S}/${GDCDIR}/check_cgroups.sh ${D}${bindir}
    install -m 0664 ${S}/${GDCDIR}/check_container_dependencies.sh ${D}${bindir}
    install -m 0664 ${S}/${GDCDIR}/check_ggc_user_and_group.sh ${D}${bindir}
    install -m 0664 ${S}/${GDCDIR}/check_kernel_configs.sh ${D}${bindir}
    install -m 0664 ${S}/${GDCDIR}/check_script_dependencies.sh ${D}${bindir}
    install -m 0664 ${S}/${GDCDIR}/check_sw_packages.sh ${D}${bindir}
    install -m 0664 ${S}/${GDCDIR}/check_system_configs.sh ${D}${bindir}
    install -m 0664 ${S}/${GDCDIR}/log_utils.sh ${D}${bindir}
    install -m 0664 ${S}/${GDCDIR}/string_utils.sh ${D}${bindir}
    install -m 0664 ${S}/${GDCDIR}/validate_platform_security.sh ${D}${bindir}
}

FILES_${PN} += "${bindir}/*"