import axios from 'axios'

const utils ={
    axiosMethod:(config)=>{
        axios({
            headers:config.headers,
            method:config.method,
            url:config.url,
            params:config.params?config.params:null,
            data:config.data?config.data:null,

        }).then(config.callback).catch(config.catch?config.catch:()=>{})
    }
}

export default utils