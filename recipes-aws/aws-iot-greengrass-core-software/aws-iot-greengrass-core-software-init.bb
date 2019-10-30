SUMMARY = "systemd service for Greengrass Core Software"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch systemd

PACKAGES = "${PN}"

SRC_URI = " \
    file://greengrass-software.service \
"

RDEPENDS_${PN} = " aws-iot-greengrass-core-software "

do_install () {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/greengrass-software.service ${D}${systemd_unitdir}/system
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "greengrass-software.service"

FILES_${PN} += " \
    /greengrass/config/* \
"
