const React = require('react');
const ReactDOM = require('react-dom');
import {
	HashRouter as Router,
	Switch,
	Link,
	Route
} from "react-router-dom";


export class SentimentDetector extends React.Component {

	constructor(props) {
		super(props);
		this.state = { status: "No text.", req: { author: givenName, text: "" }, isLoaded: true, response: { sentiment: 'neutral' } };
		this.handleInputChange = this.handleInputChange.bind(this);
		this.submitChange = this.submitChange.bind(this);
	}

	handleInputChange(event) {
		const target = event.target;
		const value = target.name === 'isGoing' ? target.checked : target.value;
		const name = target.id;
		let newReq = this.state.req;
		newReq[name] = value;
		this.setState({
			req: newReq, status: "Text changed.", response: { sentiment: 'neutral' }
		});
	}
	submitChange(event) {
		this.setState({
			status: "Busy...",
		});
		fetch("https://eforms.azure-api.net/jetdemofaas/DetectSentiment", {
			method: 'POST', body: JSON.stringify(this.state.req), headers: {
				'Content-Type': 'application/json',
				'Ocp-Apim-Subscription-Key': '65f72c21040a4a30b95712b0ded1de38',
				'Authorization': 'Bearer ' + jwtToken
			},
		})
			.then(res => res.json())
			.then(
				(result) => {
					this.setState({
						isLoaded: true,
						status: "Sentiment is " + result.sentiment + ".",
						response: result
					});
				},
				(error) => {
					this.setState({
						isLoaded: true,
						status: "Call failed.",
						error
					});
				}
			)
	}
	render() {
		if (this.state.isLoaded) {
			return (<div className="row" ><br />
				<div className="col-lg-2"></div>
				<div className="col-lg-6">
					<div className={this.state.response.sentiment}>
						<h4>Text Analytics Form</h4>
						<form>
							<div className="form-group">
								<label htmlFor="text">Text For Analysis</label>
								<textarea className="form-control" id="text" rows="3" value={this.state.req.text} onChange={this.handleInputChange}></textarea>
							</div>
						</form>
						<mark>Status: {this.state.status}</mark>
					</div>
					<button type="button" className="btn btn-primary" onClick={this.submitChange} disabled={this.state.status != 'Text changed.'}>Analyze Sentiment</button>
				</div></div>);
		}
		return (
			<div className="spinner-border" role="status">
				<span className="sr-only">Loading...</span>
			</div>
		);
	}
}