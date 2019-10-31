SUMMARY = "systemd service for Inference docker image for the AWS Pasta Demo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd python3-dir

PACKAGES = "${PN}"

# You should replace "config.passwd" contents with your own sha256 hash!
SRC_URI = " \
    file://inference-source.tar.gz \
    file://inference-python.service \
"

do_install () {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/inference-python.service ${D}${systemd_unitdir}/system

    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/${PN}
    cp -a ${WORKDIR}/inference/* ${D}${PYTHON_SITEPACKAGES_DIR}/${PN}/
    chown -R 0:0 ${D}${PYTHON_SITEPACKAGES_DIR}/${PN}
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "inference-python.service"

FILES_${PN} += " \
    ${PYTHON_SITEPACKAGES_DIR}/${PN}/* \
    ${PYTHON_SITEPACKAGES_DIR}/${PN}/common_util/* \
    ${PYTHON_SITEPACKAGES_DIR}/${PN}/model/* \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
PACKAGE_STRIP = "no"
INHIBIT_PACKAGE_STRIP = "1"

INSANE_SKIP_${PN} = "already-stripped ldflags"