SUMMARY = "Scientific Library for Python"
SECTION = "devel/python"
HOMEPAGE = "https://pypi.python.org/pypi/scipy"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=eb7262aea2504e4c0dfd16f5079e14dd"

PYPI_PACKAGE="scipy"

SRC_URI[md5sum] = "69db58ceb4b4c3ff3f3ea816e4e426b9"
SRC_URI[sha256sum] = "2643cfb46d97b7797d1dbdb6f3c23fe3402904e3c90e6facfe6a9b98d808c1b5"

#inherit pipy
inherit pypi setuptools3 distutils3

DEPENDS += " ${PYTHON_PN}-numpy ${PYTHON_PN}-numpy-native ${PYTHON_PN}-setuptools ${PYTHON_PN}-pybind11 ${PYTHON_PN}-cython openblas lapack git git-native "

RDEPENDS_${PN} += " \
    ${PYTHON_PN}-numpy \
    "

# Need to override because it doesn't accept "setup.py clean"
#ERROR: Logfile of failure stored in: /workdir/torizon/build-torizon/tmp-torizon/work/aarch64-tdx-linux/python3-scipy/1.3.1-r0/temp/log.do_configure.23774
#Log data follows:
#| DEBUG: Executing shell function do_configure
#| 
#| `setup.py clean` is not supported, use one of the following instead:
#| 
#|   - `git clean -xdf` (cleans all files)
#|   - `git clean -Xdf` (cleans all versioned files, doesn't touch
#|                       files that aren't checked into the git repo)
#| 
#| Add `--force` to your command to use it anyway if you must (unsupported).
do_configure() {
	#${STAGING_BINDIR_NATIVE}/${PYTHON_PN}-native/${PYTHON_PN} setup.py clean ${DISTUTILS_BUILD_ARGS}
    #${STAGING_BINDIR_NATIVE}/git clean -xdf
    :
}

#LAPACK=${WORKDIR}/recipe-sysroot-native/usr/lib/python3.7/site-packages/numpy/linalg/

#do_compile_prepend(){
#    cp -an ${WORKDIR}/recipe-sysroot ${WORKDIR}/recipe-sysroot-native
#}

#do_compile_prepend() {
#    echo "
#    [openblas]
#    libraries = openblas
#    library_dirs = ${WORKDIR}/recipe-sysroot/usr/lib
#    include_dirs = ${WORKDIR}/recipe-sysroot/usr/include
#    runtime_library_dirs = ${WORKDIR}/recipe-sysroot/usr/lib

#    [lapack]
#    libraries = lapack
#    library_dirs = ${WORKDIR}/recipe-sysroot/usr/lib
#    include_dirs = ${WORKDIR}/recipe-sysroot/usr/include
#    runtime_library_dirs = ${WORKDIR}/recipe-sysroot/usr/lib
#    " > ${WORKDIR}/recipe-sysroot-native/usr/lib/python3.7/site-packages/numpy/distutils/site.cfg
#}

do_compile_prepend() {
		     export PYTHONPATH="${STAGING_LIBDIR}/python2.7/site-packages"
#		     export LAPACK="${STAGING_LIBDIR}"
		     export OPENBLAS="${STAGING_LIBDIR}"
#		     export OPT="${CFLAGS}"
}

do_install() {
	     export PYTHONPATH="${STAGING_LIBDIR_NATIVE}/python2.7/site-packages"
#	     export LAPACK="${STAGING_LIBDIR}"
	     export OPENBLAS="${STAGING_LIBDIR}"
	     distutils_do_install;
}