<template>
    <div class="box">
        <div id="clock">
            <p class="date">{{ clock.date }}</p>
            <p class="time">{{ clock.time }}</p>
            <p class="text">信息工程系</p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'major',
    data() {
        return {
            clock:{
                time: '',
                date: ''
            }
        }
    },
    mounted() {
        this.updateTime();
        setInterval(this.updateTime, 1000);
    },
    methods: {
        updateTime() {
            const{clock} = this;
            var week = ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
            var cd = new Date();
            clock.time = this.zeroPadding(cd.getHours(), 2) + ':' + this.zeroPadding(cd.getMinutes(), 2) + ':' + this.zeroPadding(cd.getSeconds(), 2);
            clock.date = this.zeroPadding(cd.getFullYear(), 4) + '-' + this.zeroPadding(cd.getMonth()+1, 2) + '-' + this.zeroPadding(cd.getDate(), 2) + ' ' + week[cd.getDay()];
        },
        zeroPadding(num, digit) {
            var zero = '';
            for(var i = 0; i < digit; i++) {
                zero += '0';
            }
            return (zero + num).slice(-digit);
        }

    }
}
// var week = ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
// var timerID = setInterval(updateTime, 1000);
// updateTime();
// function updateTime() {
//     var cd = new Date();
//     clock.time = zeroPadding(cd.getHours(), 2) + ':' + zeroPadding(cd.getMinutes(), 2) + ':' + zeroPadding(cd.getSeconds(), 2);
//     clock.date = zeroPadding(cd.getFullYear(), 4) + '-' + zeroPadding(cd.getMonth()+1, 2) + '-' + zeroPadding(cd.getDate(), 2) + ' ' + week[cd.getDay()];
// };

// function zeroPadding(num, digit) {
//     var zero = '';
//     for(var i = 0; i < digit; i++) {
//         zero += '0';
//     }
//     return (zero + num).slice(-digit);
// }
</script>

<style scoped lang="less">

.box {
  background: #0f3854;
  background: -webkit-radial-gradient(center ellipse, #0a2e38 0%, #000000 70%);
  background: radial-gradient(ellipse at center, #0a2e38 0%, #000000 70%);
  background-size: 100%;
  height: 100%;
}

p {
  margin: 0;
  padding: 0;
}

#clock {
  font-family: 'Share Tech Mono', monospace;
  color: #ffffff;
  text-align: center;
  position: absolute;
  left: 60%;
  top: 50%;
  -webkit-transform: translate(-50%, -50%);
          transform: translate(-50%, -50%);
  color: #daf6ff;
  text-shadow: 0 0 20px #0aafe6, 0 0 20px rgba(10, 175, 230, 0);
}
#clock .time {
  letter-spacing: 0.05em;
  font-size: 80px;
  padding: 5px 0;
}
#clock .date {
  letter-spacing: 0.1em;
  font-size: 24px;
}
#clock .text {
  letter-spacing: 0.1em;
  font-size: 12px;
  padding: 20px 0 0;
}
</style>
