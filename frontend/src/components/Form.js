import React, { Component } from 'react';

import Text from "./Text";

class Form extends Component {
    constructor(props) {
        super(props);
        this.postDataToServlet = this.postDataToServlet.bind(this);
        this.state = {
            loading : true,
            jsonData : this.getDataFromServlet(),
            json : null
        };
    }

    postDataToServlet(event) {
        event.preventDefault();
        const data = new FormData(event.target);

        fetch('http://localhost:8083/backend/upload', {
            mode : 'cors',
            method: 'POST',
            body: data
        });

        this.setState({json : this.getDataFromServlet(), loading : false});
    }
    async getDataFromServlet() {
        const url = "http://localhost:8083/backend/data";

        const response = await fetch(url,{mode : 'cors'});
        const data = await response.json();

        this.setState({json : data, loading : false});
        //this.state.json = data;

        return data;
    }


    render() {

        var FormDiv =
            <div className="Form">
                <form id="form"onSubmit={this.postDataToServlet}>
                    <input id="uploadText" name="text" type="text" />
                    <span id="spanEnterText" htmlFor="uploadText">Enter text</span>
                    <span id="spanEnterTextBorder" className="border"></span>
                    <button>Send text!</button>
                </form>
            </div>;

        var dataDiv = <div className="Data">empty</div>;
        if(this.state.loading) {
            return(
                <div>
                    {FormDiv}
                    <div className="Data">'Loading'</div>
                </div>
            );
        }else {
            if(this.state.json == null || this.state.json.response == null){
                return (
                    <div>
                        {FormDiv}
                        <div className="Data">'Loaded, but json is null'</div>
                     </div>
                );

            }else {
                return (
                    <div className="Data">
                        {FormDiv}
                        <ul>json.status : {this.state.json.status}</ul>
                        <Text value={this.state.json.response}/>
                    </div>
                );
            }
        }
    }
}

export default Form;
