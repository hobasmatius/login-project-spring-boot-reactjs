'use strict';

const React = require('react');
const ReactDOM = require('react-dom');

class Home extends React.Component {

	constructor(props) {
		super(props);
	}
	
	logout() {
		window.location('/');
	}

	render() {
		return (
		  <button type="button" onclick="{this.logout}">Logout</button>
		)
	}
}

ReactDOM.render(
	<Home />,
	document.getElementById('home')
)