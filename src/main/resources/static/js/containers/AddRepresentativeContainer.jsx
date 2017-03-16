var AddRepresentativeContainer = React.createClass({

    getInitialState: function() {

        return {
            representative: {
                countyId: this.props.params.countyId,
                districtId: this.props.params.id,
                firstName: '',
                lastName: '',
                password: '',
                userName: ''
            },

            representativeAuthData: {
                id: this.props.params.id,
                userName: "",
                password: "",
                role: "USER"
            }
        };

    },

    handleOnChangeInputRepresentativeData: function(field) {
        var _this = this;
        return function(e) {
            e.preventDefault();
            var representative = _this.state.representative;
            var representativeAuthData = _this.state.representativeAuthData;
            representative[field] = e.target.value;
            representativeAuthData[field] = e.target.value;
            _this.setState({representative: representative, representativeAuthData: representativeAuthData});
        };
    },

    handleOnSubmitAddRepresentativeData: function() {

        var _this = this;

        axios.post('/api/representative', this.state.representative).then(function(response) {
            console.log("representative created");
            document.getElementById("representativeCreationDialog").innerHTML = `<div class="alert alert-success">
             <strong>Atstovas sėkmingai užregistruotas</strong>
             </div>`;

        }).catch(function(error) {
            document.getElementById("representativeCreationDialog").innerHTML = `<div class="alert alert-danger">
             <strong>Nepavyko užregistruoti atstovo</strong>
             </div>`;
            console.log(error.response.data);
        });

        axios.post('/api/auth', this.state.representativeAuthData).then(function(response) {
            console.log("representative ROLE created");
        }).catch(function(error) {
            console.log(error.response.data);
        });


    },

    render: function() {

        return (
            <div>

                <AddRepresentativeComponent
                    districtId={this.props.params.countyId}
                    onSubmitAddRepresentativeData={this.handleOnSubmitAddRepresentativeData}
                    onChangeInputRepresentativeData={this.handleOnChangeInputRepresentativeData}/>

            </div>
        );
    }
});


window.AddRepresentativeContainer = AddRepresentativeContainer;
