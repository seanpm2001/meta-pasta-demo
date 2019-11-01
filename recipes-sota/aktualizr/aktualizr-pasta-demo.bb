SUMMARY = "Aktualizr credentials for the Pasta Demo OTA server"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " file://credentials.zip;unpack=0 \
            file://server.toml \
"

do_install() {
    install -m 0700 -d ${D}${sysconfdir}/sota/conf.d
    install -m 0644 ${WORKDIR}/credentials.zip ${D}${sysconfdir}/sota/conf.d
    install -m 0644 ${WORKDIR}/server.toml ${D}${sysconfdir}/sota/conf.d
}

FILES_${PN} += " \
    ${sysconfdir}/sota/conf.d/credentials.zip \
    ${sysconfdir}/sota/conf.d/server.toml \
"