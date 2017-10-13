import React, { Component } from 'react'

class IncomingRequest extends Component {
  constructor() {
    super()
    this.approveRequest = this.approveRequest.bind(this)
  }
  approveRequest(evt) {
    evt.preventDefault()
    evt.stopPropagation()
    //ajax request
  }
  render() {
    const request = this.props.request
    return (
      <div className="IncomingRequest">
        <img src={request.user.photo} />
        <h4>{request.user.name}</h4>
        <div className="approveBtn">
          <button
            onClick={this.approveRequest}
          >
          </button>
        </div>
      </div>
    )
  }
}

export default IncomingRequest
