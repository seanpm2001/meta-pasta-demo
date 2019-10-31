SUMMARY = "Python library that provides an easy interface to read and write a \
wide range of image data, including animated images, video, volumetric data, \
and scientific formats."
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d8b7fdd0dff0fd18f35c05365d3d7bf7"

SRC_URI = " \
    https://files.pythonhosted.org/packages/69/4a/0387d708394d5e25d95b1abe427c301614152d1bebea18d9b06fa7199704/imageio-${PV}.tar.gz \
"
SRC_URI[md5sum] = "4d3d6f826778a07c15daf5ff83df9990"
SRC_URI[sha256sum] = "42e65aadfc3d57a1043615c92bdf6319b67589e49a0aae2b985b82144aceacad"

S = "${WORKDIR}/imageio-${PV}"

inherit setuptools3

DEPENDS = "python3"

RDEPENDS_${PN} = "python3-numpy python3-pillow"

