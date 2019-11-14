import React, { Component } from 'react';

class Data extends Component {
    state = {
        loading : true,
        jsonData : this.getData(),
        json : null
    };
    async componentDidMount() {
        const url = "http://localhost:8083/backend/data";

        const response = await fetch(url, {mode: 'cors'});
        const data = await response.json();

        //this.setState({json : data, loading : false});
        this.state.json = data;
        this.setState({json : data, loading : false});
        console.log("json.getData");
        console.log(this.state.json);
    }

    async getData() {
        const url = "http://localhost:8083/backend/data";

        const response = await fetch(url,{mode : 'cors'});
        const data = await response.json();

        this.setState({json : data, loading : false});
        this.state.json = data;
        console.log("json.getData");
        console.log(this.state.json);
    }

    render() {

        if(this.state.loading) {
            return 'Loading...'
        }else {
            if(this.state.json == null ){
                return (
                    <div className="Data">
                        Loaded, but json is null
                    </div>
                );
            }else {
                return (
                    <div className="Data">
                        <ul>json.status : {this.state.json.status}</ul>
                        <ul>json.text.text : {this.state.json.text.text}</ul>
                    </div>
                );
            }

        }
    }
}

export default Data;
