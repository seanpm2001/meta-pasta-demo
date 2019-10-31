DESCRIPTION = "DLR is a compact, common runtime for deep learning models and \
decision tree models compiled by AWS SageMaker Neo, TVM, or Treelite. DLR uses \
the TVM runtime, Treelite runtime, NVIDIA TensorRT™, and can include other \
hardware-specific runtimes. DLR provides unified Python/C++ APIs for loading \
and running compiled models on various devices. DLR currently supports \
platforms from Intel, NVIDIA, and ARM, with support for Xilinx, Cadence, and \
Qualcomm coming soon."
SUMMARY = "Python bindings for DLR: Compact Runtime for Machine Learning Models"
HOMEPAGE = "https://github.com/neo-ai/neo-ai-dlr"
SECTION = "libs"
LICENSE = "Apache-2.0"

DEPENDS = " sagemaker-neo-ai-dlr "

RDEPENDS_${PN} = "python3-numpy python3-requests python3-threading python3-ctypes"

LIC_FILES_CHKSUM = "file://../LICENSE;md5=34400b68072d710fecd0a2940a0d1658"

SRC_URI = " gitsm://github.com/neo-ai/neo-ai-dlr.git;protocol=https "
# Release 1.0
SRCREV = "dd9c8e806065b2c0fca97209c8bfd1cce0749ea9"

S = "${WORKDIR}/git/python"

do_configure_prepend() {
    #cp ${WORKDIR}/recipe-sysroot/${libdir}/libmxnet.so ${S}/mxnet/libmxnet.so
    ln -s ${WORKDIR}/recipe-sysroot/${libdir}/libdlr.so ${S}/dlr/libdlr.so
}

do_install_append() {
    rm -r ${D}${datadir}

    # Remove actual library duplicate and create a symlink instead
    install -d ${D}${libdir}/python3.7/site-packages/dlr
    rm ${D}${libdir}/python3.7/site-packages/dlr/libdlr.so
    ln -s ${libdir}/libdlr.so ${D}${libdir}/python3.7/site-packages/dlr/libdlr.so
}

FILES_${PN} += " ${libdir}/python3.7/site-packages/dlr/libdlr.so "

INSANE_SKIP_${PN} += "dev-so"

inherit setuptools3