const React = require('react');
const ReactDOM = require('react-dom');

export class LoginForm extends React.Component {

	constructor(props) {
		super(props);
		this.state = { user : { userName: "", userPassword: ""} };
		this.handleInputChange = this.handleInputChange.bind(this);	
	}

	handleInputChange(event) {
		const target = event.target;
		const value = target.name === 'isGoing' ? target.checked : target.value;
		const name = target.id;
		let usr = this.state.user;
		usr[name] = value;
		this.setState({ user: usr });
	}
	render() {
		return (<div className="container-fluid"><center><div className="row" ><div className="col-lg-4">
			<div>
				<br />
				<h4>Login</h4>
				<form action="../idp/authorize">
					<div className="form-group">
						<label htmlFor="userName">User Name</label>
						<input className="form-control" id="userName" type="text" value={this.state.user.userName} onChange={this.handleInputChange}></input>						
					</div>
					<div className="form-group">
						<label htmlFor="userPassword">Password</label>
						<input className="form-control" id="userPassword" type="password" value={this.state.user.userPassword} onChange={this.handleInputChange}></input>						
					</div>
					<input type="submit" className="btn btn-primary" value="Submit" />
				</form>
			</div>
		</div></div></center></div>);
	}
}