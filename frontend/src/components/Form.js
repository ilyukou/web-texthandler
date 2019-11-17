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
            json : null,
            errorTextArea : false
        };

    }

    postDataToServlet(event) {
        event.preventDefault();

        if(document.getElementById('field5').value === ""){
            console.log("empty");
            this.setState({json : null, errorTextArea : true});
        }else {
            this.setState({errorTextArea : false});
            const data = new FormData(event.target);

            fetch('http://localhost:8083/backend/data', {
                mode : 'cors',
                method: 'POST',
                body: data
            });

            //this.setState({json : this.getDataFromServlet(), loading : false});
        }
    }

    async getDataFromServlet() {
        this.setState({loading : true, errorTextArea : false});
        const url = "http://localhost:8083/backend/data";

        const response = await fetch(url,{mode : 'cors'});
        const data = await response.json();

        this.setState({json : null, loading : false});
        this.setState({json : data, loading : false});
    }
    async getSortedDataFromServlet(){

        this.setState({loading : true, errorTextArea : false});
        const url = "http://localhost:8083/backend/sort";

        const response = await fetch(url,{mode : 'cors'});
        const data = await response.json();

        this.setState({json : null, loading : false});
        this.setState({json : data, loading : false});
    }

    render() {
        var buttonGetData = <button className="btn" type="button"
                                    onClick={this.getDataFromServlet}>Get</button>;
        var buttonGetSortedData = <button className="btn btn-second" type="button"
                                          onClick={this.getSortedDataFromServlet}>Get Sorted</button>;
        if(this.state.errorTextArea === true){
            var error = <div className="textAreaIsEmpty">TextArea value is empty</div>;
        }else {
            var error = <div></div>;
        }

        var form = <form onSubmit={this.postDataToServlet} >
            <ul className="form-style-1">
                <li>
                    <label>Your Text <span className="required">*</span></label>
                    <textarea name="field5" id="field5" placeholder="Enter your text"
                              className="field-long field-textarea">{}</textarea>
                </li>
                <li>
                    <input type="submit" value="Upload"/>
                    {buttonGetData}
                    {buttonGetSortedData}
                </li>
                <li>{error}</li>
            </ul>
        </form>;

      if(this.state.json == null || this.state.json.response == null){
            return(
                <div className="NewForm">
                    {form}
                </div>
            );
        }else {
            return (
                <div className="NewForm">
                    {form}
                    <Text value={this.state.json}/>
                </div>
            );
        }

    }
}export default Form;