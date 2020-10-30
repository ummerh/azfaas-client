const React = require('react');
const ReactDOM = require('react-dom');
export class HomeWindow extends React.Component {
	constructor(props) {
		super(props);
		this.state = { currTab: "" };
	}
	render() {
		return (
			<div>
				<main role="main">
					<section className="jumbotron text-center">
						<div className="container">
							<h1>Azure Functions as a Service - Demo</h1>
							<p className="lead">Serverless architecture evolves cloud platforms toward pure cloud-native code by abstracting code from the infrastructure that it needs to run. Azure Functions is a serverless compute option that supports functions, small pieces of code that do single things.</p>
							<p>
								<a href="https://github.com/ummerh/azfaas-client" className="btn btn-primary" target="_blank">
									{'  '}
									<svg className="octicon octicon-mark-github v-align-middle" height="22" viewBox="0 0 16 16" version="1.1" width="22" aria-hidden="true"><path fillRule="evenodd" d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0016 8c0-4.42-3.58-8-8-8z"></path></svg>
									{'  '}Web App Code</a>
								{'    '}
								<a href="https://github.com/ummerh/azfaas" className="btn btn-primary" target="_blank">
									{'  '}
									<svg className="octicon octicon-mark-github v-align-middle" height="22" viewBox="0 0 16 16" version="1.1" width="22" aria-hidden="true"><path fillRule="evenodd" d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0016 8c0-4.42-3.58-8-8-8z"></path></svg>
									{'  '}Logic App Code</a>
								{'    '}
								<a href="https://docs.microsoft.com/en-us/azure/architecture/serverless-quest/serverless-overview" className="btn btn-secondary" target="_blank">More from Azure</a>
							</p>
							<p className="lead text-muted">The Azure infrastructure automatically provides all the updated servers that applications need to keep running at scale.</p>
							<p className="lead text-muted">Compute resources allocate dynamically, and instantly autoscale to meet elastic demands. Serverless doesn't mean "no server," but "less server," because servers run only as needed</p>
							<p className="lead text-muted">Micro-billing saves costs by charging only for the compute resources and duration the code uses to execute.</p>
							<p className="lead text-muted">Function bindings streamline integration by providing declarative access to a wide variety of Azure and third-party services.</p>
						</div>
					</section>
					<div className="container text-center">
						<h2>Reference Architecture</h2>
						<p><img className="img-fluid" src="images/azfaas-architecture.svg" type="image/svg+xml"></img></p>
					</div>
					<div className="container">
						<div className="row">
							<div className="col-md-4">
								<h2>Features</h2>
								<p>This application aims to convey some of the cloud-native application characteristics that are listed below;</p>
								<p><b>OpenID Connect</b> - is an authentication protocol, built on top of OAuth 2.0, that can be used to securely sign users in to web applications. By using the Azure Active Directory B2C (Azure AD B2C) implementation of OpenID Connect, you can outsource sign-up, sign-in, and other identity management experiences in your web applications to Azure Active Directory (Azure AD).</p>
								<p><b>Single Page Applications (SPA)</b>  - is a GUI design paradigm where UI will be delivered as JS components and data loaded on demand through API calls. Modern javascript libraries are supporting a UXI that is pleasant and highly interactive. </p>
								<p><b>API</b> based microservices are highly distributed, scalable, domain-centric services delivered as Application Programming Interfaces, typically using technologies like HTTPS and REST. </p>
								<p><a className="btn btn-secondary" href="#" role="button">View details »</a></p>
							</div>
							<div className="col-md-4">
								<h2>Technologies</h2>
								<ul>
									<li>Java</li>
									<li>NodeJS</li>
									<li>ReactJS</li>
									<li>Spring Boot</li>
									<li>Spring Security Oauth 2.0</li>
									<li>Bootstrap</li>
									<li>Docker</li>
									<li>Azure Functions - Java SDK</li>
									<li>Azure AI Text Anaytics</li>
									<li>Azure App Services</li>
								</ul>
								<p><a className="btn btn-secondary" href="#" role="button">View details »</a></p>
							</div>
							<div className="col-md-4">
								<h2>Instructions</h2>
								<ul>
									<li>Setup workspace</li>
									<li>Setup Azure container registry</li>
									<li>Git clone source code</li>
									<li>mvn clean package</li>
									<li>docker build</li>
									<li>docker tag</li>
									<li>acr login</li>
									<li>Docker push to acr</li>
									<li>Deploy to Azure App Service</li>
								</ul>
								<p><a className="btn btn-secondary" href="#" role="button">View details »</a></p>
							</div>
						</div>
						<hr />
					</div>
				</main>
				<footer className="container">
					<p>Jackson Enterprise Architecture 2019-2020</p>
				</footer>
			</div >
		);
	}
}