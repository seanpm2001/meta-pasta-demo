SUMMARY = "systemd service for Inference docker image for the AWS Pasta Demo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd python3-dir

PACKAGES = "${PN}"

# You should replace "config.passwd" contents with your own sha256 hash!
SRC_URI = " \
    git://github.com/toradex/aws-nxp-ai-at-the-edge.git;protocol=https;name=source \
    https://docs.toradex.com/106832-pasta-demo-inference-model.tar.bz2;name=model \
    file://inference-python.service \
"

SRCREV = "124fc1a838f29ebbf71f41559ec30771d8b614f4"
SRC_URI[model.md5sum] = "53c185197f31d0ab48642b46834b836c"
SRC_URI[model.sha256sum] = "51b33977e858f0b6ff0c4ba7167ff8a70c885d31e34a80e71cdec10d4f9557cb"

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