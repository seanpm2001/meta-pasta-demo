DESCRIPTION = "A Flask extension adding a decorator for JSONP support"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=a835553a299ffc78d25306f9c487aa89"

SRC_URI[md5sum] = "8a10e37942c43d93d107644a3fe77d98"
SRC_URI[sha256sum] = "8ac4c732aa5b11d9f6c2de58065d3b669f139518ca8f529bce943817e2fedbfb"

PYPI_PACKAGE = "Flask-Jsonpify"

inherit pypi

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-flask \
    "