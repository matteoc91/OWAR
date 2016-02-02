<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-md-12">
		<div class="form-group">
			<label class="text-info" for="officeAddress">Address (*)</label>
			<input type="text" id="officeAddress" class="userInput form-control">
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="form-group">
			<label class="text-info" for="officeHomeNumber">Home Number (*)</label>
			<input type="number" min="1" id="officeHomeNumber" class="userInput form-control">
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="form-group">
			<label class="text-info" for="officeDistrict">District (*)</label>
			<select id="officeDistrict" class="userInput form-control">
				<option></option>
			</select>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="form-group">
			<label class="text-info" for="officeDescription">Description (*)</label>
			<textarea id="officeDescription" class="userInput form-control" row="5"></textarea>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="form-group">
			<label class="text-info" for="officeDailyPrice">Daily Price (*)</label>
			<input type="text" id="officeDailyPrice" class="userInput form-control">
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="form-group">
			<label class="text-info" for="officeBeginDate">Begin Rent (*)</label>
			<input type="date" id="officeBeginDate" class="userInput form-control">
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="form-group">
			<label class="text-info" for="officeEndDate">End Rent (*)</label>
			<input type="date" id="officeEndDate" class="userInput form-control">
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12" id="newServiceInList"></div>
</div>
<div class="row">
	<div class="col-md-12" id="newOfficeImgList">
		<form:form id="newOfficeImgListForm" role="form" commandName="newOfficeImgListForm" method="POST" action="" enctype="multipart/form-data">
			<div class='form-group'>
				<label class='text-info'>New Image</label>
				<input type='file' class='officeImg' name="image0">
			</div>
			<div class='form-group'>
				<label class='text-info'>New Image</label>
				<input type='file' class='officeImg' name="image1">
			</div>
			<div class='form-group'>
				<label class='text-info'>New Image</label>
				<input type='file' class='officeImg' name="image2">
			</div>
			<div class='form-group'>
				<label class='text-info'>New Image</label>
				<input type='file' class='officeImg' name="image3">
			</div>
		</form:form>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<button type="button" class="btn btn-default" id="addServiceBtn">Add Service</button>
		<button type="button" class="btn btn-primary" id="resetBtn">Reset</button>
		<button type="button" class="btn btn-primary" id="newOfficeBtn">Create Office</button>
	</div>
</div>