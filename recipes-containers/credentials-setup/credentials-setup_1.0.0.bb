SUMMARY = "systemd service for Greengrass Credentials Updater"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS_${PN} = "aws-iot-greengrass-core-software"

inherit allarch systemd

PACKAGES = "${PN}"

# You should replace "config.passwd" contents with your own sha256 hash!
SRC_URI = " \
    file://docker-compose.yml \
    file://greengrass-credentials.service \
"

do_install () {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/greengrass-credentials.service ${D}${systemd_unitdir}/system

    install -d ${D}/${sysconfdir}/docker-compose
    install -m 0644 ${WORKDIR}/docker-compose.yml ${D}/${sysconfdir}/docker-compose/docker-compose-credentials-setup.yml
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "greengrass-credentials.service"

FILES_${PN} += " \
    /greengrass/config/* \
    ${sysconfdir}/docker-compose/docker-compose-credentials-setup.yml \
"
