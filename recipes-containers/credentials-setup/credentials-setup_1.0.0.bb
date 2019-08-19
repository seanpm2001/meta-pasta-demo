SUMMARY = "systemd service for Greengrass Credentials Updater"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch systemd

PACKAGES = "${PN}"

SRC_URI = " \
    file://docker-compose.yml \
    file://config.passwd \
    file://config.disable \
    file://greengrass-credentials.service \
"

do_install () {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/greengrass-credentials.service ${D}${systemd_unitdir}/system

    install -d ${D}/greengrass/config/
    install -m 0644 ${WORKDIR}/config.passwd ${D}/greengrass/config
    install -m 0644 ${WORKDIR}/config.disable ${D}/greengrass/config

    install -m 0644 ${WORKDIR}/docker-compose.yml {D}/docker-compose-credentials-setup.yml
    install -d ${D}/greengrass/certs/
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "greengrass-credentials.service"
