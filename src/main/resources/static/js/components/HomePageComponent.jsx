var Link = ReactRouter.Link;
var Well = ReactBootstrap.Well;
var Modal = ReactBootstrap.Modal;
var Button = ReactBootstrap.Button;

var HomePageComponent = React.createClass( {

    getInitialState() {
        return {showModal: false, showSecondModal: false,ShowThirdModal: false,districts: [],
            singleMemberDistrictsCompletedVoting:[],
            multiMemberDistrictsCompletedVoting:[]};
    },

    close() {
        this.setState({showModal: false,showSecondModal: false,ShowThirdModal: false});
    },
    openModal() {
        this.setState({showModal: true});
        setTimeout(function() {
            this.setState({showModal: false});
        }.bind(this), 2000);
    },

    componentDidMount: function() {
        this.onHover();
    },
    componentDidUpdate: function() {
        this.onHover();
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
    handle:function(){
        this.openModal();

        
    },
    handleVotingCompleted:function(){
        
        if(this.state.singleMemberDistrictsCompletedVoting.length == this.state.districts.length &&
                this.state.multiMemberDistrictsCompletedVoting.length == this.state.districts.length 
                && this.state.multiMemberDistrictsCompletedVoting.length !=0 && this.state.singleMemberDistrictsCompletedVoting.length !=0){
            
            
          return <div className="col-md-3 col-sm-6 col-xs-12" >
          <Link to="/finalResultSingleMemberElected" style={{ textDecoration: 'none' }}>   <div className="feature-box pulsate">
          <div className="icon">
              <i className="homeIcon glyphicon glyphicon-indent-right"></i>
          </div>
          <h3>Galutiniai rinkimų rezultatai</h3>
      </div> </Link>
  </div>
            
        }else{
            return <div className="col-md-3 col-sm-6 col-xs-12" onClick={this.handle} >
              <div className="feature-box pulsate">
            <div className="icon">
                <i className="homeIcon glyphicon glyphicon-indent-right"></i>
            </div>
            <h3>Galutiniai rinkimų rezultatai</h3>
        </div> 
    </div>
        }
        
        
    },

    render: function( props ) {
        return (
            <div>
                
                
                <div className="row title">
                    <div className="text-center">
                        <div>Rinkimų Svetainė</div>
                    </div>
                </div>
                <Well>

                    <section className="content-block container content tg">
                        <Link to="/county-list" className="icon">
                            <div className="col-md-3 col-sm-6 col-xs-12">
                                <div className="feature-box pulsate">
                                    <div className="icon">
                                        <i className="homeIcon glyphicon glyphicon-align-left"></i>
                                    </div>
                                    <h3>Apygardų sąrašas</h3>
                                </div>
                            </div>
                        </Link>
                        
                                    
                                    
                                   {this.handleVotingCompleted()} 
                                    
                                    
                                    
                        <div className="col-md-3 col-sm-6 col-xs-12">
                            <Link to="/singleMember/main-result" style={{ textDecoration: 'none' }}>  <div className="feature-box pulsate">
                                <div className="icon">
                                    <i className="homeIcon glyphicon glyphicon-user"></i>
                                </div>

                                <h3>Vienmandatės apygardos rezultatai</h3>

                            </div> </Link>
                        </div>

                        <div className="col-md-3 col-sm-6 col-xs-12">
                            <Link to="/multiMember/main-result" style={{ textDecoration: 'none' }}> <div className="feature-box pulsate">
                                <div className="icon">
                                    <i className="homeIcon glyphicon glyphicon-th"></i>
                                </div>

                                <h3>Daugiamandatės apygardos rezultatai</h3>

                            </div> </Link>
                        </div>
                    </section>
                </Well>

                                <Modal show={this.state.showModal} bsSize="small" onHide={this.close}>
                                <Modal.Header style={{ borderBottom:'0px'}}  closeButton>
                                    <Modal.Title id="contained-modal-title-sm">Balsavimas vis dar nesibaigė.</Modal.Title>
                                </Modal.Header>

                            </Modal>
            </div>
        );
    },

    onHover: function() {
        $( '.icon', ).hover( function() {
            $( this ).addClass( 'animated pulse' )
        }, function() {
            $( this ).removeClass( 'animated pulse' )
        });

    }
});

window.HomePageComponent = HomePageComponent;
