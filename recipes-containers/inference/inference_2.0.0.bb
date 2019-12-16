SUMMARY = "systemd service for AI inference for the AWS Pasta Demo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch systemd

PACKAGES = "${PN}"

SRC_URI = " \
    file://inference.service \
"

do_install () {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/inference.service ${D}${systemd_unitdir}/system
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "inference.service"
