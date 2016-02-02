var LocationFilter = function() {
	
	var FilterObj = this;
	
	this.district = null;
	this.town = null;
	this.region = null;
	
	this.init = function() {
		FilterObj
		if($("#officeFilterDistrict").closest(".locationFilter").is(":visible") && $("#officeFilterDistrict").val() != "") {
			FilterObj.district = $("#officeFilterDistrict").find(":selected").attr("name");
		} else {
			FilterObj.district = null;
		}
		if($("#officeFilterTown").closest(".locationFilter").is(":visible") && $("#officeFilterTown").val() != "") {
			FilterObj.town = $("#officeFilterTown").find(":selected").attr("name");
		} else {
			FilterObj.town = null;
		}
		if($("#officeFilterRegion").closest(".locationFilter").is(":visible") && $("#officeFilterRegion").val() != "") {
			FilterObj.region = $("#officeFilterRegion").find(":selected").attr("name");
		} else {
			FilterObj.region = null;
		}
	}
	
	this.createJSON = function() {
		var json = {};
		if(FilterObj.district != null) {
			json.district = FilterObj.district
		}
		if(FilterObj.town != null) {
			json.town = FilterObj.town;
		}
		if(FilterObj.region != null) {
			json.region = FilterObj.region;
		}
		return json;
	}
	
}

var ServicesFilter = function() {
	
	var FilterObj = this;
	
	this.services = [];
	
	this.init = function(containerId) {
		FilterObj.services = [];
		var i = 0;
		$("#"+containerId+" .singleService").each(function(index, item) {
			var singleService = {};
			if(!$(item).find(".serviceId").val() == "") {
				singleService.service_id = $(item).find(".serviceId").find(":selected").attr("name");
				singleService.num_service = $(item).find(".serviceNumber").val();
				FilterObj.services[i++] = singleService;
			}
		});
	}
	
	this.createJSON = function() {
		return FilterObj.services;
	}
	
}