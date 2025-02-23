SUMMARY = "Tool for creating Zynq boot images"
HOMEPAGE = "https://github.com/antmicro/zynq-mkbootimage"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c9c77a4b70683658eb47e5486e20148"

# Use git repository
SRC_URI = "git://github.com/antmicro/zynq-mkbootimage.git;protocol=https;branch=master"
SRCREV = "872363ce32c249f8278cf107bc6d3bdeb38d849f"

S = "${WORKDIR}/git"

inherit native

# No configuration step needed
do_configure () {
    :
}

# Compile with a simple make invocation
do_compile () {
    oe_runmake
}

# Install the binary into the native bindir
do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${S}/mkbootimage ${D}${bindir}
}

FILES_${PN} = "${bindir}/mkbootimage"