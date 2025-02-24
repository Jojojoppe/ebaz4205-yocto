FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

UBOOT_MACHINE:ebaz4205-zynq7 = "zynq_ebaz4205_defconfig"
SRC_URI:append:ebaz4205-zynq7 = " \
    file://0001-ebaz4205-added-files.patch \
    "
    