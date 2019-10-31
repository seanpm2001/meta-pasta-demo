SUMMARY = "systemd service for System Control docker image for the AWS Pasta Demo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch systemd

PACKAGES = "${PN}"

# You should replace "config.passwd" contents with your own sha256 hash!
SRC_URI = " \
    file://system-control.service \
    file://docker-compose.yml \
"

do_install () {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/system-control.service ${D}${systemd_unitdir}/system

    install -d ${D}/${sysconfdir}/docker-compose
    install -m 0644 ${WORKDIR}/docker-compose.yml ${D}/${sysconfdir}/docker-compose/docker-compose-system-control.yml
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "system-control.service"

FILES_${PN} += " \
    ${sysconfdir}/docker-compose/docker-compose-system-control.yml \
"
