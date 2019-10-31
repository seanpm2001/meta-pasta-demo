SUMMARY = "systemd service for Inference docker image for the AWS Pasta Demo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch systemd

PACKAGES = "${PN}"

# You should replace "config.passwd" contents with your own sha256 hash!
SRC_URI = " \
    file://inference.service \
    file://docker-compose.yml \
"

do_install () {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/inference.service ${D}${systemd_unitdir}/system

    install -d ${D}/${sysconfdir}/docker-compose
    install -m 0644 ${WORKDIR}/docker-compose.yml ${D}/${sysconfdir}/docker-compose/docker-compose-inference.yml
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "inference.service"

FILES_${PN} += " \
    ${sysconfdir}/docker-compose/docker-compose-inference.yml \
"
