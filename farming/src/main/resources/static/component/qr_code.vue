<template>
    <!--二维码-->
    <div clas="box">
        <el-dialog
                :key="dialogVisible"
                title="二维码"
                :visible.sync="dialogVisible"
                width="28%">
            <div id="qrcodeBox">
                <div id="qrcode"></div>
            </div>
            <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="print()">打 印</el-button>
            <el-button type="primary" icon="el-icon-download" @click="download()">下 载</el-button>
        </span>
        </el-dialog>
    </div>
</template>
<script>
    module.exports = {
        name: "qr_code",
        props: {
            peid: String,
        },
        watch: {
            //监听菜单切换
            dialogVisible: function (val) {
                if (val) {
                    this.init();
                }
            }
        },
        data() {
            return {
                dialogVisible: false,
            }
        },
        mounted() {

        },
        methods: {
            //打印二维码
            print(){
                var newWindow=window.open("打印窗口","_blank");
                var docStr = document.querySelector('#qrcode').innerHTML;
                newWindow.document.write(docStr);
                newWindow.document.close();
                newWindow.print();
                newWindow.close();
            },

            //下载二维码
            download(){
                let dataUrl = document.querySelector('#qrcode').querySelector('img').src;
                var link = document.createElement("a");
                link.download = 'qrcode.png';
                link.href = dataUrl;
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
                delete link;
            },
            downloadURI(uri, name) {
                var link = document.createElement("a");
                link.download = name;
                link.href = uri;
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
                delete link;
            },

            //初始化
            init() {
                setTimeout(res => {
                    var qrcode = new QRCode(document.getElementById("qrcode"), {
                        width: document.getElementById("qrcodeBox").offsetWidth,
                        height: 350,
                    });
                    qrcode.makeCode('http://'+ip+"/roots.html?id="+this.peid);
                }, 100)
            }
        }
    }
</script>

<style scoped>

</style>