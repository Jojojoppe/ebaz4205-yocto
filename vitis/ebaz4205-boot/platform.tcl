# 
# Usage: To re-create this platform project launch xsct with below options.
# xsct /data/joppe/projects/ebaz/vitis/ebaz4205-boot/platform.tcl
# 
# OR launch xsct and run below command.
# source /data/joppe/projects/ebaz/vitis/ebaz4205-boot/platform.tcl
# 
# To create the platform in a different location, modify the -out option of "platform create" command.
# -out option specifies the output directory of the platform project.

platform create -name {ebaz4205-boot}\
-hw {/home/joppe/data/projects/ebaz/vivado/boot/toplevel_wrapper.xsa}\
-proc {ps7_cortexa9_0} -os {standalone} -out {/data/joppe/projects/ebaz/vitis}

platform write
platform generate -domains 
platform active {ebaz4205-boot}
platform generate
