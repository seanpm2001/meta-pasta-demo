SRC_URI = "file://${PN}-${PV}-py2.py3-none-any.zip;subdir=${PN}-${PV}"

SRC_URI[md5sum] = "6cd851054b86ae92782a2e7aef22c839"
SRC_URI[sha256sum] = "e86caf498cab19d05c7978efb8739036b4d8f9ead3842f015f1163af23dcc0a6"

PN = "dlr"

#inherit nativesdk python3-dir
inherit python3-dir

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file:///${S}/${PN}-${PV}.dist-info/METADATA;md5=5c4d46c2845c7782ffc674aba6a3688d"


PROVIDES += " sagemaker-neo-ai-dlr-wheel"
RPROVIDES_${PN} +=" sagemaker-neo-ai-dlr-wheel"
DEPENDS += "${PYTHON_PN} imx-gpu-viv"

do_install() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/${PN}-${PV}.dist-info
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/${PN}
    install -d ${D}/${libdir}

    install -m 644 ${S}/${PN}/* ${D}${PYTHON_SITEPACKAGES_DIR}/${PN}
    install -m 644 ${S}/${PN}-${PV}.dist-info/* ${D}${PYTHON_SITEPACKAGES_DIR}/${PN}-${PV}.dist-info
    install -m 644 ${S}/${PN}-${PV}.data/data/dlr/libdlr.so ${D}/${libdir}/lib${PN}.so.${PV}
    cd ${D}${libdir}
    ln -s lib${PN}.so.${PV} lib${PN}.so
    cd ${D}${PYTHON_SITEPACKAGES_DIR}/${PN}
    ln -s ${libdir}/lib${PN}.so.${PV} lib${PN}.so
}

FILES_${PN} += "\
    ${PYTHON_SITEPACKAGES_DIR}/* \
    ${libdir}/libdlr.so* \
"

INSANE_SKIP_${PN} += "dev-so"
