DESCRIPTION = "GluonCV provides implementations of the state-of-the-art (SOTA) \
deep learning models in computer vision."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI[md5sum] = "c5644b9d01df4c7906177e632a0ff5f3"
SRC_URI[sha256sum] = "4b9733e5cb173f2de37111ff6d836ff2be14bdbd97df59af97ee3c459df99b39"

PYPI_PACKAGE = "gluoncv"

inherit pypi

DEPENDS += " mxnet "

RDEPENDS_${PN} += "\
    mxnet ${PYTHON_PN}-mxnet \
    ${PYTHON_PN}-matplotlib \
    ${PYTHON_PN}-tqdm \
    ${PYTHON_PN}-numpy \
    ${PYTHON_PN}-pillow \
    ${PYTHON_PN}-requests \
    ${PYTHON_PN}-pyparsing \
    "
# ${PYTHON_PN}-scipy