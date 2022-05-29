'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class Login extends React.Component {

	constructor(props) {
		super(props);
		this.state = { 
			usernameOrEmail: '',
			password: ''
		};
	}
	
	onSubmit(event) {
		event.preventDefault();
		
		let usernameOrEmail = event.target.elements.usernameOrEmail.value;
		let password = event.target.elements.password.value;
		
		client({
			path: '/login',
			headers: { "Content-Type": "application/json" },
			entity: { "usernameOrEmail": usernameOrEmail, "password": password }
		}).done(response => {	
			if (response.entity.code === '200' && response.entity.message === "OK") {
				window.location = '/home'
			} else {
				alert(response.entity.message)
			}
		});
	}

	render() {
		return (
			<form onSubmit={this.onSubmit}>
			  <div className="container">
			    <label htmlFor="usernameOrEmail"><b>Username/Email</b></label>
			    <input type="text" placeholder="Username or Email" name="usernameOrEmail" required/>
			    
			    <br/>
			    
			    <label htmlFor="password"><b>Password</b></label>
			    <input type="password" placeholder="Password" name="password" required/>
			
				<br/>
			
			    <input type="submit" value="Login"/>
			  </div>
			</form>
		)
	}
}

ReactDOM.render(
	<Login />,
	document.getElementById('login')
)