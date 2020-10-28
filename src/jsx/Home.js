const React = require('react');
const ReactDOM = require('react-dom');
const HomeWindow = require("./HomeWindow.js").HomeWindow;
const Navigation = require("./Navigation.js").Navigation;

import {
	HashRouter as Router,
	Switch,
	Route
} from "react-router-dom";

export class Home extends React.Component {
	render() {
		return (
			<Router>
				<div>
					<Navigation path="." />
					<Switch>
						<Route path="/" exact>
							<HomeWindow />
						</Route>
					</Switch>
				</div>
			</Router>
		);
	}
}