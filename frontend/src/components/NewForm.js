import React, { Component } from 'react';
import './NewForm.css';
import Text from "./Text";

class NewForm extends Component {
    constructor(props) {
        super(props);
        this.postDataToServlet = this.postDataToServlet.bind(this);
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

        // upload div of Form
        this.getDataFromServlet();
    }

    async getDataFromServlet() {
        const url = "http://localhost:8083/backend/data";

        const response = await fetch(url,{mode : 'cors'});
        const data = await response.json();

        this.setState({json : data, loading : false});

        return data;
    }

    render() {
        if(this.state.json == null || this.state.json.response == null){
            return(
                <div className="NewForm">
                    <form onSubmit={this.postDataToServlet}>
                        <ul className="form-style-1">
                            <li>
                                <label>Your Message <span className="required">*</span></label>
                                <textarea name="field5" id="field5" className="field-long field-textarea"></textarea>
                            </li>
                            <li>
                                <input type="submit" value="Submit"/>
                            </li>
                        </ul>
                    </form>
                </div>
            );
        }else {
            return (
                <div className="NewForm">
                    <form onSubmit={this.postDataToServlet}>
                        <ul className="form-style-1">
                            <li>
                                <label>Your Message <span className="required">*</span></label>
                                <textarea name="field5" id="field5" className="field-long field-textarea"></textarea>
                            </li>
                            <li>
                                <input type="submit" value="Submit"/>
                            </li>
                        </ul>
                    </form>
                    <Text value={this.state.json.response}/>
                </div>
            );
        }

    }
}export default NewForm;