import React, { Component } from 'react';
import './Form.css';
import Text from "./Text";

class Form extends Component {
    constructor(props) {
        super(props);
        this.postDataToServlet = this.postDataToServlet.bind(this);
        this.getDataFromServlet = this.getDataFromServlet.bind(this);
        this.getSortedDataFromServlet = this.getSortedDataFromServlet.bind(this);

        this.state = {
            loading : true,
            json : null
        };

    }

    postDataToServlet(event) {
        event.preventDefault();
        //const data = document.getElementById('field5').value;
        const data = new FormData(event.target);
        fetch('http://localhost:8083/backend/upload', {
            mode : 'cors',
            method: 'POST',
            body: data
        });

        this.setState({json : this.getDataFromServlet(), loading : false});
    }

    async getDataFromServlet() {
        this.setState({loading : true});
        const url = "http://localhost:8083/backend/data";

        const response = await fetch(url,{mode : 'cors'});
        const data = await response.json();

        this.setState({json : null, loading : false});
        this.setState({json : data, loading : false});
    }
    async getSortedDataFromServlet(){
        this.setState({loading : true});
        const url = "http://localhost:8083/backend/sort";

        const response = await fetch(url,{mode : 'cors'});
        const data = await response.json();

        this.setState({json : null, loading : false});
        this.setState({json : data, loading : false});
    }

    render() {
        //  onSubmit={this.postDataToServlet}
        var form = <form onSubmit={this.postDataToServlet} >
            <ul className="form-style-1">
                <li>
                    <label>Your Text <span className="required">*</span></label>
                    <textarea name="field5" id="field5" placeholder="Enter your text"
                              className="field-long field-textarea">{}</textarea>
                </li>
                <li>
                    <input type="submit" value="Upload"/>
                </li>
            </ul>
        </form>;

        var buttonGetData = <button className="submit btn" type="submit"
                                    onClick={this.getDataFromServlet}>Get Data</button>;
        var buttonGetSortedData = <button className="submit btn" type="submit"
                                          onClick={this.getSortedDataFromServlet}>Get Sorted Data</button>;
        // var buttonGetData = <input className="form-style-1" type="submit"
        //                             onClick={this.getDataFromServlet} value="Get Data"/>;
        // var buttonGetSortedData = <input className="form-style-1" type="submit"
        //                                   onClick={this.getSortedDataFromServlet} value="Get Sorted Data"/>;
        if(this.state.json == null || this.state.json.response == null){
            return(
                <div className="NewForm">
                    {form}
                    {buttonGetData}
                    {buttonGetSortedData}
                </div>
            );
        }else {
            return (
                <div className="NewForm">
                    {form}
                    {buttonGetData}
                    {buttonGetSortedData}
                    <Text value={this.state.json}/>
                </div>
            );
        }

    }
}export default Form;