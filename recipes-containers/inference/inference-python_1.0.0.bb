SUMMARY = "systemd service for Inference docker image for the AWS Pasta Demo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd python3-dir

PACKAGES = "${PN}"

# You should replace "config.passwd" contents with your own sha256 hash!
SRC_URI = " \
    git://github.com/toradex/aws-nxp-ai-at-the-edge.git;protocol=https;name=source \
    https://docs.toradex.com/106832-pasta-demo-inference-model.tar.bz2?v=2;name=model \
    file://inference-python.service \
"

SRCREV = "1b1e12cc8b6013367caf1fd0ed47f105c03a26be"
SRC_URI[model.md5sum] = "d36eaa713244c986e8e1f5cd1b44300f"
SRC_URI[model.sha256sum] = "697d7993b66784be5f2878ef3b147424104187785af88c482d070ffe90a403b9"

do_install () {
    # Install systemd service
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/inference-python.service ${D}${systemd_unitdir}/system

    # Install inference source-code
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/${PN}
    cp -a ${WORKDIR}/git/inference/* ${D}${PYTHON_SITEPACKAGES_DIR}/${PN}/

    # Install inference model
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/${PN}/model
    cp -a ${WORKDIR}/pasta-demo-inference-model/* ${D}${PYTHON_SITEPACKAGES_DIR}/${PN}/model/

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