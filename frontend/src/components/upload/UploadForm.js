import React, { Component } from 'react';

class UploadForm extends Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);

        fetch('http://localhost:8083/backend/upload', {
            mode : 'cors',
            method: 'POST',
            body: data
        });
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label htmlFor="username">Enter text</label>
                <input id="username" name="text" type="text" />

                <button>Send text!</button>
            </form>
        );
    }
}
/*
<form onSubmit={this.handleSubmit}>
                <label htmlFor="username">Enter text</label>
                <input id="username" name="text" type="text" />

                <button>Send text!</button>
            </form>
 */
export default UploadForm;