import React, { Component } from 'react';
import './Text.css';
// props is array of paragraphs object from server without response status
class Text extends Component {
    state = {
        paragraphs : this.props.value.response.paragraphs
    };

    getTextDiv(){
        return this.state.paragraphs.map((paragraph) =>
            <p className="paragraphs">
                {this.getParagraphsContainsSentences(paragraph)}
            </p>
        );
    }
    getParagraphsContainsSentences(paragraph){
        return  paragraph.sentenceList.map((sentence) =>
            <div className="sentence">
                {this.getSentenceContainsTextElements(sentence)}
            </div>
        );
    }
    getSentenceContainsTextElements(sentenceList){
        return sentenceList.textElements.map((textElement) =>
                <span className={textElement.textElementType+' textElement'}>
                    {textElement.value}
                </span>
        );
    }

    render() {
        if(this.state.paragraphs === undefined){
            return (
                <div className="Text">
                    Text Component Loading...
                </div>
            );
        }else {
            return (
                <div className="Text">
                    {this.getTextDiv()}
                </div>
            );
        }
    }
}export default Text;

