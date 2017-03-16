var Link = ReactRouter.Link;
var Accordion = ReactBootstrap.Accordion;
var Panel = ReactBootstrap.Panel;
var Button = ReactBootstrap.Button;
var Modal = ReactBootstrap.Modal;
var Well = ReactBootstrap.Well;
var id = 0;


var AdminPageComponent = React.createClass({


    getInitialState() {
        return {showModal: false, 
            showSecondModal: false,
            showThirdModal: false,
            showFourthModal: false,
            districts: [],
            singleMemberDistrictsCompletedVoting:[],
            multiMemberDistrictsCompletedVoting:[],
            endVoteCounting: false
            
        
        };
        
    },

    close() {
        this.setState({showModal: false,showSecondModal: false,showThirdModal: false,showFourthModal: false});
    },
    openFirstModal() {
        this.setState({showModal: true});
    },
    openSecondModal() {
        this.setState({showSecondModal: true});
    },
    openThirdModal() {
        this.setState({showThirdModal: true});
    },
    openFourthModal() {

        this.setState({showThirdModal:false, showFourthModal: true});
    },
    onChangeMultiPartyListFileInput: function() {
        this.props.onChangeMultiPartyListFileInput(this.refs.multiPartyListFile.files[0]);
    },

    onChangeSinglePartyListFileInput: function() {
        this.props.onChangeSinglePartyListFileInput(this.refs.singlePartyListFile.files[0]);
    },

    getID(){
        $(".classNameToGetId").click(function(){
            id = this.id;
            console.log(id);
        });
    },

    
    componentWillMount:function(){
        var _this = this;
        axios.get('/api/district').then(function(response) {
            _this.setState({districts: response.data});
        }).catch(function(error) {
            console.log(error);
        });
        
      
        axios.get('/repr/api/singlevotescorrupt').then(function(response) {
            _this.setState({singleMemberDistrictsCompletedVoting: response.data});
        }).catch(function(error) {
            console.log(error);
        });
        
       
        
        axios.get('/repr/api/multivotescorrupt').then(function(response) {
            _this.setState({multiMemberDistrictsCompletedVoting: response.data});
        }).catch(function(error) {
            console.log(error);
        });
    },
    
    handleStartVoteCount:function(){
        
        if(this.state.singleMemberDistrictsCompletedVoting.length == this.state.districts.length &&
                this.state.multiMemberDistrictsCompletedVoting.length == this.state.districts.length 
                && this.state.multiMemberDistrictsCompletedVoting.length !=0 && this.state.singleMemberDistrictsCompletedVoting.length !=0){
            
            
          return true;
            
        }else{
            return false;
        }
 },
 handleStartVoteCounting: function() {

     var _this = this;

    
     axios.get( '/results/single/winner/all' ).then( function( response ) {
         console.log("a" );
         
         axios.get( '/results/multi/winnerswinners' ).then( function( response ) {
             console.log("b" );
             axios.get( 'results/single/countyWinners' ).then( function( response ) {
                 console.log("c" );
                 
                 axios.get( '/results/multi/allFinalPartyResult' ).then( function( response ) {
                     console.log("d" );
                     
                     axios.get( 'results/multi/allPartyWinners' ).then( function( response ) {
                         console.log("e" );
                         _this.setState({endVoteCounting:true});
                     }).catch( function( error ) {
                         console.log( error );
                     });
                     
                 }).catch( function( error ) {
                     console.log( error );
                 });
                 
             }).catch( function( error ) {
                 console.log( error );
             });
             
         }).catch( function( error ) {
             console.log( error );
         });
         
     }).catch( function( error ) {
         console.log( error );
     });
    
 },
        
    render: function() {

        
        var props = this.props;
        var _this = this;

        var buttonStartVoteCounting =  "";
        
        
        if(_this.handleStartVoteCount()){
            buttonStartVoteCounting = <button className="btn btn-primary row"
                onClick={_this.handleStartVoteCounting}>Skaičiuoti rezultatus</button>;
        }
        
        if(_this.state.endVoteCounting){
            buttonStartVoteCounting ="";
        }
        
        
        var partyListWithRemove = this.props.parties.map(function(party, index) {
            if (props.partyList(party)) {
            return (
                <option key={index} value={party.partyId}>
                    {party.partyName}
                    (NR.{party.partyId})
                </option>

            );
            }
        });
        
        var partyList = this.props.parties.map(function(party, index) {
            
            return (
                <option key={index} value={party.partyId}>
                    {party.partyName}
                    (NR.{party.partyId})
                </option>

            );
            
        });



        var countyList = _this.props.counties.map(function(county) {

            return (

                <tr key={county.countyId}>
                    <td>
                        <div className="tableText">
                            <Link to={'/county/districts/' + county.countyId}>{county.countyName}</Link>
                        </div>
                    </td>
                    <td>
                        <button style={{
                            float: 'right',
                            marginTop:'15px',
                            marginBottom:'10px'
                        }} type="button" className="classNameToGetId btn btn-danger" id={county.countyId} onMouseOver={_this.getID} onClick={_this.openThirdModal}>TRINTI</button>
                    </td>
                </tr>
            //props.onClickDeleteCounty(county)
            );
        });

        var deleteDistrict =
                <Modal show={_this.state.showThirdModal} bsSize="small" onHide={_this.close}>
                <Modal.Header closeButton></Modal.Header>
                <Modal.Body>
                    <Modal.Title id="countyDeletionRequest"> Ar tikrai norite ištrinti apygardą?</Modal.Title>
                </Modal.Body>
                <Modal.Footer>
                    <Button id="countyDeletionConfirm" className="btn-danger" onClick={props.onClickDeleteCounty(id)} onMouseUp={_this.openFourthModal}>Trinti</Button>
                    <Button onClick={_this.close}>Atšaukti</Button>
                </Modal.Footer>
            </Modal>;
        var districtDeletionConfirmation =
            <Modal show={_this.state.showFourthModal} bsSize="small" onHide={_this.close}>
            <Modal.Header style={{ borderBottom:'0px'}}  closeButton><Modal.Title id="countyDeletionMessage">Apygarda ištrinta</Modal.Title></Modal.Header>

        </Modal>;



        var singlePartyCountyList = _this.props.counties.map(function(county) {
            if (props.countyList(county)) {

            return (
                <option key={county.countyId} value={county.countyId}>
                    {county.countyName}
                </option>
            );
            }
        });
        var countyCandidateList = _this.props.counties.map(function(county) {

            if (props.countyDeleteList(county)) {

                return (
                    <option key={county.countyId} value={county.countyId}>
                        {county.countyName}
                    </option>
                );
            }

        });

        return (
            <div className="container">
                {deleteDistrict}
                {districtDeletionConfirmation}
                <div className="title">Administratoriaus puslapis</div>
                <Well className="row">

                    <Accordion className="col-md-8 col-md-offset-2 col-xs-12 ">

                    <Panel id="partyRegistration" header="Partijos registravimas" eventKey="1" className="adminPanel">
                        <form id="partyAddForm" onSubmit={props.onSubmitAddParty}>

                            <div className="form-group">
                                <label htmlFor="exampleInput1">Partijos pavadinimas</label>
                                <input type="text"  className="form-control" required id="exampleInput1" onChange={props.onChangeInputPartyData('partyName')}/>
                            </div>

                            <div className="form-group">
                                <label htmlFor="exampleInputNumber">Partijos Nr.
                                </label>&nbsp;
                                <input type="number" min="0" className="form-control-file"required id="exampleInputNumber" onChange={props.onChangeInputPartyData('partyId')}/>
                            </div>

                            <button type="submit" className="btn btn-primary">
                                <strong>Sukurti</strong>
                            </button>

                            <div id="partyAddFormMsg" style={{
                                marginTop: '20px'
                            }}></div>
                        </form>
                    </Panel>

                    <Panel id="partyListRegistration" header="Partijos sąrašo registravimas" eventKey="2" className="adminPanel">
                        <form id="partyCandidateListAddForm" onSubmit={props.onSubmitMultiPartyList}>

                            <div className="form-group">
                                <label htmlFor="exampleSelect1">Partijos pavadinimas</label>
                                <select id="dropdownOne"    className="form-control" name="partyId" onChange={props.onChangeSelectPartyIdForCandidateList}>
                                    <option defaultValue >Pasirinkite partiją</option>
                                    {partyListWithRemove}
                                </select>
                            </div>
                            <div className="form-group">
                                <label htmlFor="exampleInputFile">Partijos kandidatų sąrašas (CSV)</label>
                                <input onChange={this.onChangeMultiPartyListFileInput} accept=".csv" ref="multiPartyListFile" type="file" name="multiPartyListFile"/>
                            </div>
                            <button type="submit" className="btn btn-primary">
                                <strong>Įkelti</strong>
                            </button>
                        </form>
                        <div id="partyListAddFormMsg" style={{
                            marginTop: '20px'
                        }}></div>
                    </Panel>

                    <Panel id="partyListDeletion" header="Partijos ir jos sąrašo trynimas" eventKey="3" className="adminPanel" style={{
                        marginBottom: '50px'
                    }}>
                        <form id="partyCandidateListDeleteForm" onSubmit={props.onSubmitDeleteParty}>
                            <div className="form-group">
                                <label htmlFor="exampleSelect1">Partijos pavadinimas(Partijos NR.)</label>
                                <select id="dropdownTwo"  className="form-control" onChange={props.onChangeInputPartyIdToDelete}>
                                    <option  defaultValue >Pasirinkite partiją, kurią trinsite</option>
                                    {partyList}
                                </select>
                            </div>
                            <Button onClick={this.openFirstModal} className="btn btn-danger">Ištrinti</Button>
                        </form>
                        <div id="partyDeletionMsg" style={{
                            marginTop: '20px'
                        }}></div>
                    </Panel>
                    </Accordion>

                    <Accordion className="col-md-8 col-md-offset-2 col-xs-12 ">

                     <Panel id="countyRegistration" header="Naujos apygardos registravimas" eventKey="4" className="adminPanel">
                            <form id="countyAddForm" onSubmit={props.onSubmitAddCounty}>
                                <div className="form-group">
                                    <label htmlFor="exampleInput" >Apygardos pavadinimas</label>
                                    <input type="text" className="form-control" required id="exampleInput" onChange={props.onChangeInputCountyData('countyName')}/>
                                </div>

                                <button type="submit" className="btn btn-primary">
                                    <strong>Sukurti</strong>
                                </button>
                                <div id="countyAddFormMsg" style={{
                                    marginTop: '20px'
                                }}></div>
                            </form>
                        </Panel>


                    <Panel id="countyListRegistration" header="Apygardos kandidatų sąrašo registravimas" eventKey="5"  className="adminPanel">
                        <form id="countyCandidateListAddForm" onSubmit={props.onSubmitSinglePartyList}>
                            <div className="form-group">
                                <label htmlFor="exampleSelect1" >Apygardos pavadinimas</label>
                                <select id="dropdownThree"   className="form-control" onChange={props.onChangeSelectCountyIdForCandidateList}>
                                    <option  defaultValue >Pasirinkite apygardą</option>
                                    {singlePartyCountyList}
                                </select>
                            </div>
                            <div className="form-group">
                                <label htmlFor="exampleInputFile">Apygardos kandidatų sąrašas (CSV)</label>
                                <input onChange={_this.onChangeSinglePartyListFileInput} type="file" accept=".csv" ref="singlePartyListFile" name="singlePartyListFile"/>
                            </div>
                            <input type="submit" className="btn btn-primary" value="Pridėti"/>
                        </form>
                        <div id="countyListMsg" style={{
                            marginTop: '20px'
                        }}></div>
                    </Panel>

                    <Panel id="countyListDeletion" header="Apygardos kandidatų sąrašo trynimas" eventKey="6" className="adminPanel" style={{
                                marginBottom: '50px'
                                }}>
                                <form onSubmit={props.onSubmitDeleteCountyCandidateList}>
                                    <div className="form-group">
                                        <label htmlFor="exampleSelect1">Apygardos pavadinimas</label>
                                        <select id="dropdownFour"    className="form-control" onChange={props.onChangeInputCountyListIdToDelete}>
                                            <option  defaultValue >Pasirinkite apygardą, kurios kandidatų sąrašą trinsite</option>
                                            {countyCandidateList}
                                        </select>
                                    </div>
                                    <Button onClick={this.openSecondModal} className="btn btn-danger">Ištrinti</Button>

                                </form>
                                <div id="countyListDeletionMsg" style={{
                                    marginTop: '20px'
                                }}></div>
                    </Panel>

                </Accordion>
                                    
                                   {buttonStartVoteCounting}
                </Well>

                <Well className="row">
                <div>
                    <h2 className="subtitle">Esamos apygardos</h2><br/>

                    <table className="table table-hover">
                        <thead>
                            <tr>
                                <th>Pavadinimas</th>
                                <th></th>

                            </tr>
                        </thead>
                        <tbody >
                            {countyList}
                        </tbody>
                    </table>

                </div>
                </Well>
                <Modal show={this.state.showModal} bsSize="small" onHide={this.close}>
                    <Modal.Header closeButton></Modal.Header>
                    <Modal.Body>
                        <Modal.Title id="contained-modal-title-sm">Ar tikrai norite ištrinti partiją ir jos sąrašą?</Modal.Title>
                    </Modal.Body>
                    <Modal.Footer closeButton>
                        <Button className="btn-danger" onClick={props.onSubmitDeleteParty} onMouseUp={this.close}>Trinti</Button>
                        <Button onClick={this.close}>Close</Button>
                    </Modal.Footer>
                </Modal>

                <Modal show={this.state.showSecondModal} bsSize="small" onHide={this.close}>
                    <Modal.Header closeButton></Modal.Header>
                    <Modal.Body>
                        <Modal.Title id="contained-modal-title-sm">Ar tikrai norite ištrinti apygardos kandidatų sąrašą?</Modal.Title>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button className="btn-danger" onClick={props.onSubmitDeleteCountyCandidateList} onMouseUp={this.close}>Trinti</Button>
                        <Button onClick={this.close}>Close</Button>
                    </Modal.Footer>
                </Modal>


            </div>
        );
    }
});

window.AdminPageComponent = AdminPageComponent;
