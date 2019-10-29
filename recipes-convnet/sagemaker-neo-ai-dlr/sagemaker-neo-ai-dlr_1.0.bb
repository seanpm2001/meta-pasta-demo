DESCRIPTION = "DLR is a compact, common runtime for deep learning models and \
decision tree models compiled by AWS SageMaker Neo, TVM, or Treelite. DLR uses \
the TVM runtime, Treelite runtime, NVIDIA TensorRT™, and can include other \
hardware-specific runtimes. DLR provides unified Python/C++ APIs for loading \
and running compiled models on various devices. DLR currently supports \
platforms from Intel, NVIDIA, and ARM, with support for Xilinx, Cadence, and \
Qualcomm coming soon."
SUMMARY = "DLR: Compact Runtime for Machine Learning Models"
HOMEPAGE = "https://github.com/neo-ai/neo-ai-dlr"
SECTION = "libs"
LICENSE = "Apache-2.0"

#DEPENDS = " openblas lapack opencv ninja-native "
DEPENDS = " imx-gpu-viv "

LIC_FILES_CHKSUM = "file://LICENSE;md5=34400b68072d710fecd0a2940a0d1658"

SRC_URI = " gitsm://github.com/neo-ai/neo-ai-dlr.git;protocol=https "
# Release 1.0
SRCREV = "dd9c8e806065b2c0fca97209c8bfd1cce0749ea9"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE = " \
    -DUSE_OPENCL=ON \
"

do_compile_prepend() {
    EXTRA_INCLUDES="-I${WORKDIR}/recipe-sysroot-native/usr/lib/aarch64-tdx-linux/gcc/aarch64-tdx-linux/9.2.0/include \
        -I${WORKDIR}/recipe-sysroot-native/usr/lib/aarch64-tdx-linux/gcc/aarch64-tdx-linux/9.2.0/include-fixed \
        -I${WORKDIR}/recipe-sysroot/usr/include/c++/9.2.0 \
        -I${WORKDIR}/recipe-sysroot/usr/include/c++/9.2.0/aarch64-tdx-linux \
        -I${WORKDIR}/recipe-sysroot/usr/include/c++/9.2.0/backward \
        -I${WORKDIR}/recipe-sysroot/usr/lib/aarch64-tdx-linux/9.2.0/include \
        -I${WORKDIR}/recipe-sysroot/usr/include"
    sed '/^\ \ INCLUDES/ s@$@\ '"${EXTRA_INCLUDES}"'@' ${B}/build.ninja > ${B}/custom-build.ninja
}

do_compile() {
	ninja -v ${PARALLEL_MAKE} -f ${B}/custom-build.ninja
}

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${includedir}
    DESTDIR=${D} ninja -v ${PARALLEL_MAKE} -f ${B}/custom-build.ninja install
}

do_install_append() {
    cp ${S}/lib/libdlr.so ${D}${libdir}/libdlr.so.${PV}
    cd ${D}${libdir}
    ln -s libdlr.so.${PV} libdlr.so

    cp ${S}/include/* ${D}${includedir}
}

FILES_${PN} += " \
    ${libdir}/libdlr.so \
"

FILES_${PN}-dev += " \
    ${includedir}/* \
"