FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

UBOOT_MACHINE = "zynq_ebaz4205_defconfig"

SRC_URI:append = " \
    file://0001-ebaz4205_defconfig.patch \
    "