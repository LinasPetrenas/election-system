var Link = ReactRouter.Link;
var Well = ReactBootstrap.Well;
var Button = ReactBootstrap.Button;
var Modal = ReactBootstrap.Modal;
var id = 0;

var DistrictPageComponent = React.createClass({

    getInitialState() {
        return {showModal: false, showSecondModal: false,showThirdModal: false,showFourthModal: false};
    },

    close() {
        this.setState({showModal: false,showSecondModal: false,showThirdModal: false,showFourthModal: false});
    },
    openFirstModal() {
        this.setState({showModal: true});
    },
    openSecondModal() {
        this.setState({showModal: false,showSecondModal: true});
        setTimeout(function() {
            this.setState({showSecondModal: false});
        }.bind(this), 3000);
    },
    openThirdModal() {
        this.setState({showThirdModal: true});
    },
    openFourthModal() {
        this.setState({showThirdModal:false, showFourthModal: true});
        setTimeout(function() {
            this.setState({showFourthModal: false});
        }.bind(this), 3000);
    },
    getID(){
        $(".classNameToGetId").click(function(){
            id = this.id;
            console.log(id);
        });
    },

    render: function() {

        var props = this.props;
        var _this = this;

        var deleteCounty =
            <Modal show={_this.state.showModal} bsSize="small" onHide={_this.close}>
                <Modal.Header closeButton></Modal.Header>
                <Modal.Body>
                    <Modal.Title id="contained-modal-title-sm"> Ar tikrai norite ištrinti apylinkę?</Modal.Title>
                </Modal.Body>
                <Modal.Footer>
                    <Button className="btn-danger" onClick={props.onClickDeleteDistrict(id)} onMouseUp={_this.openSecondModal}>Trinti</Button>
                    <Button onClick={_this.close}>Close</Button>
                </Modal.Footer>
            </Modal>;
        var countyDeletionConfirmation =
            <Modal show={_this.state.showSecondModal} bsSize="small" onHide={_this.close}>
                <Modal.Header style={{ borderBottom:'0px'}}  closeButton><Modal.Title id="contained-modal-title-sm">Apylinkė ištrinta</Modal.Title></Modal.Header>
            </Modal>;


        var deleteRepresentative =
            <Modal show={_this.state.showThirdModal} bsSize="small" onHide={_this.close}>
                <Modal.Header closeButton></Modal.Header>
                <Modal.Body>
                    <Modal.Title id="contained-modal-title-sm"> Ar tikrai norite ištrinti apylinkės atstovą?</Modal.Title>
                </Modal.Body>
                <Modal.Footer>
                    <Button className="btn-danger" onClick={props.onClickDeleteRepresentative(id)} onMouseUp={_this.openFourthModal}>Trinti</Button>
                    <Button onClick={_this.close}>Close</Button>
                </Modal.Footer>
            </Modal>;
        var representativeDeletionConfirmation =
            <Modal show={_this.state.showFourthModal} bsSize="small" onHide={_this.close}>
                <Modal.Header style={{ borderBottom:'0px'}}  closeButton><Modal.Title id="contained-modal-title-sm tester">Apylinkės atstovas ištrintas</Modal.Title></Modal.Header>
            </Modal>;



        var districtList = this.props.districts.map(function(district) {
          var buttonState =<Link to={"/add-representative/county/" + props.countyId + "/" + district.id}>
              <button className="btn btn-success">Pridėti atstovą</button>
              </Link>;
              var deleteRepresentative=<div className="tableFix">
                  <button className="btn btn-danger disabled">Trinti atstovą</button>
              </div>;


            if (props.reprButton(district)) {
                buttonState =<div>{props.reprFullName(district)}</div>;
                deleteRepresentative=<div className="tableFix">
                    <button id={district.id}  className="classNameToGetId btn btn-danger " onMouseOver={_this.getID} onClick={_this.openThirdModal}>Trinti atstovą</button>
                </div>;
                }
            
            return (
                <tr key={district.id}>
                    <td className="tableText">
                        <div>{district.districtName}</div>
                    </td>
                    <td className="tableText">
                        <div>{district.districtAddress}</div>
                    </td>
                    <td className="tableText">
                        <div>{district.districtElectors}</div>
                    </td>
                    <td>
                        <div className="tableFix">

                        {buttonState}

                        </div>
                    </td>
                    <td>
                        {deleteRepresentative}
                    </td>
                    <td>
                        <div className="tableFix">
                        <button id={district.id} style={{
                            marginLeft: '20px'
                        }} className="classNameToGetId btn btn-danger" onMouseOver={_this.getID} onClick={_this.openFirstModal}>Trinti apylinkę</button>
                        </div>
                    </td>
                </tr>
            );
        });

        return (

            <div className="container">
                {deleteCounty}
                {countyDeletionConfirmation}
                {deleteRepresentative}
                {representativeDeletionConfirmation}
                <div className="text-center title">Apygardos pavadinimas: {this.props.county.countyName}<br/></div>
                <Well>
                <h3 className="text-center subtitle" style={{marginTop:'0'}}>Apylinkės registravimas<br/><hr/></h3>
                <div style={{width:'30%', margin:'0 auto'}}>
                <form id="districtAddForm" onSubmit={props.onSubmitAddDistrict}>
                    <div className="form-group row">
                        <label htmlFor="example-text-input" className="col-2 col-form-label">Apylinkės pavadinimas</label>
                        <div className="col-10">
                            <input className="form-control" required type="text" id="example-text-input" onChange={props.onChangeInputDistrictData('districtName')}/>
                        </div>
                    </div>
                    <div className="form-group row">
                        <label htmlFor="example-search-input" className="col-2 col-form-label">Apylinkės adresas</label>
                        <div className="col-10">
                            <input className="form-control" required type="text" id="example-search-input" onChange={props.onChangeInputDistrictData('districtAddress')}/>
                        </div>
                    </div>
                    <div className="form-group row">
                        <label htmlFor="example-email-input" className="col-2 col-form-label">Rinkėjų skaičius</label>
                        <div className="col-10">
                            <input className="form-control" required type="number" id="example-email-input" min="0" onChange={props.onChangeInputDistrictData('districtElectors')}/>
                        </div>
                    </div>
                    <button type="submit" className="btn btn-primary">
                        <strong>Sukurti</strong>
                    </button>
                    <div id="districtCreatedMsg" style={{
                        marginTop: '20px'
                    }}></div>
                </form>
                        </div>
                </Well>


                <br/>
                <Well className="row">
                    <div>
                <div className="subtitle">Apylinkių sąrašas</div>
                <hr/>
                <br/>

                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Pavadinimas</th>
                            <th>Adresas</th>
                            <th>Rinkėjų skaičius</th>
                            <th>Atstovas</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody >
                        {districtList}
                    </tbody>
                </table>
                    </div>
                </Well>
            </div>
        );
    }
});

window.DistrictPageComponent = DistrictPageComponent;
