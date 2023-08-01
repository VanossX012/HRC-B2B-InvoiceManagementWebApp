import { DataGrid } from "@material-ui/data-grid";
import "./styles.css";
import "./header.css";
import React, { useState, useEffect } from "react";
import { Button } from "@material-ui/core";
import { makeStyles, withStyles } from "@material-ui/core/styles";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  Tabs,
  Tab,
  Grid,
} from "@material-ui/core";

import axios from "axios";

const columns = [
  { field: "Sl_No", headerName: "Sl NO", width: 120, sortable: false },
  {
    field: "customerOrderId",
    headerName: "CUSTOMER ORDER ID",
    width: 250,
    sortable: false,
  },
  {
    field: "salesOrg",
    headerName: "SALES ORG",
    width: 175,
    type: "string",
    sortable: false,
  },
  {
    field: "distrChannel",
    headerName: "DISTRIBUTION CHANNEL",
    width: 250,
    type: "string",
    sortable: false,
  },
  {
    field: "compCode",
    headerName: "COMPANY CODE",
    width: 200,
    sortable: false,
  },
  {
    field: "orderCreationDate",
    headerName: "ORDER CREATION DATE",
    width: 250,
    sortable: false,
  },
  {
    field: "orderAmt",
    headerName: "ORDER AMOUNT",
    width: 200,
    sortable: false,
  },
  {
    field: "orderCurrency",
    headerName: "ORDER CURRENCY",
    width: 225,
    type: "string",
    sortable: false,
  },
  {
    field: "customerNumber",
    headerName: "CUSTOMER NUMBER",
    width: 225,
    sortable: false,
  },
  {
    field: "amountUSD",
    headerName: "AMOUNT IN USD",
    width: 200,
    sortable: false,
  },
];
const useStyles = makeStyles({
  root: {
    color: "white",
    backgroundColor: "#666666",
    "& .MuiCheckbox-root": {
      color: "white",
    },
    "& .MuiDataGrid-footerContainer": {
      minHeight: "0",
    },
    "&  .MuiDataGrid-iconSeparator": {
      visibility: "hidden",
    },
    "& .Mui-checked .MuiSvgIcon-root": {
      color: "orange",
    },
  },
  text: {
    fontSize: "1em",
  },
  border: {
    fontSize: "medium",
  },
});
const ColorButton = withStyles((theme) => ({
  root: {
    borderRadius: 5,
    color: theme.palette.getContrastText("#fc7500"),
    backgroundColor: "#fc7500",
    marginLeft: 20,
    "&:hover": {
      backgroundColor: "#fc7500",
    },
  },
}))(Button);

export default function Datatable() {
  const [currentRow, setCurrentRow] = useState(null);
  const [showDialog, setshowDialog] = useState(false);
  const [openDialog, setOpenDialog] = useState(false);
  const [currentTab, setCurrentTab] = useState(0);
  const [select, setSelection] = useState([])
  let [data, setdata] = useState([]);
  const [pageSize, setPageSize] = React.useState(5);
  const [formValues, setFormValues] = useState({
    Sl_No: "",
    customerOrderId: "",
    salesOrg: "",
    distributionChannel: "",
    customerNumber: "",
    companyCode: "",
    orderCurrency: "",
    amountInUSD: "",
    orderCreationDate: "",
    orderAmount:""
  });
  function getUsers() {
    axios
      .get("http://localhost:8080/h2h_milestone_3/Fetchallusers")
      .then((response) => response.data)
      .then((data) => {
        setdata(data);
      });
  }
  useEffect(() => {
    getUsers();
  }, []);

  var ReloadData = () => getUsers();

  const handleChangeTab = (event, newValue) => {
    setCurrentTab(newValue);
  };

  const handleOpenDialog = () => {
    setOpenDialog(true);
  };

  const handleCloseDialog = () => {
    setOpenDialog(false);
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormValues((prevValues) => ({
      ...prevValues,
      [name]: value
    }));
  };

  const handleRowSelection = (e) => {
    setSelection(e);
    if (e.length > 0) setCurrentRow(true);
    else setCurrentRow(false);
    console.log(select);
  };

  const classes = useStyles();

  const handleAddData = () => {
    axios
      .get("http://localhost:8080/h2h_milestone_3/AddUser", formValues, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        // Handle the response as needed
        console.log("Data added successfully");
        getUsers(); // Refresh the data after adding a new user
      })
      .catch((error) => {
        // Handle any errors that occur during the API call
        console.error("Error adding data:", error);
      })
      .finally(() => {
        handleCloseDialog();
      });
  };

  return (
    <div className="Controlpanel" style={{ margin: 15 }}>
      <Dialog open={showDialog}>
        <DialogTitle>Update Table Data</DialogTitle>
        <DialogContent style={{ display: "grid" }}>
          <TextField label="SI No" placeholder="Enter the SI No" name="Sl_No" />
          <TextField
            label="Customer Order Id"
            placeholder="Enter the Customer Order ID"
            name="customerOrderId"
          />
          <TextField
            label="Sales Org"
            placeholder="Enter the Sales Organisation"
            name="salesOrg"
          />
          <TextField
            label="Distr Channel"
            placeholder="Enter the Distribution Channel"
            name="distrChannel"
          />
          <TextField
            label="Comp Code"
            placeholder="Enter the Company Code"
            name="compCode"
          />
          <TextField
            label="Order Creation Date"
            placeholder="Enter the Order Creation Date"
            name="orderCreationDate"
          />
          <TextField
            label="Order Amount"
            placeholder="Enter the Order Amount"
            name="orderAmt"
          />
          <TextField
            label="Order Currency"
            placeholder="Enter the Order Currency"
            name="orderCurrency"
          />
          <TextField
            label="Customer Number"
            placeholder="Enter the Customer Number"
            name="customerNumber"
          />
          <TextField
            label="Amount in USD"
            placeholder="Enter the Amount in USD"
            name="amountUSD"
          />
        </DialogContent>
        <DialogActions>
          <Button color="primary" variant="contained">
            Update
          </Button>
          <Button
            color="primary"
            variant="contained"
            onClick={() => setshowDialog(false)}
          >
            Cancel
          </Button>
        </DialogActions>
      </Dialog>
      <Tabs value={currentTab} onChange={handleChangeTab} style={{backgroundColor:"#666666"}}>
        <Tab label="HOME PAGE" />
        <Tab label="ADD DATA" />
        <Tab label="ANALYTICS VIEW" />
      </Tabs>
      {currentTab === 0 && (
        <div className="dataTable">
          <DataGrid
            classes={{
              root: classes.root,
              cell: classes.text,
              columnHeader: classes.border,
            }}
            rows={data}
            columns={columns}
            pageSize={pageSize}
            onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
            rowsPerPageOptions={[5, 10, 20]}
            getRowId={(row) => row.Sl_No}
            onSelectionModelChange={handleRowSelection}
            selectionModel={select}
            checkboxSelection
            disableSelectionOnClick
            autoHeight
          />
          <div
        className="refesh"
        style={{ padding: 10, backgroundColor: "#666666", borderRadius: 1 }}
      >
        <ColorButton
          variant="contained"
          color="primary"
          className={classes.margin}
          onClick={ReloadData}
        >
          Refresh
        </ColorButton>
        <ColorButton
          variant="contained"
          className={classes.margin}
          disabled={!currentRow}
          onClick={() => setshowDialog(true)}
        >
          Edit
        </ColorButton>
        <ColorButton
          variant="contained"
          className={classes.margin}
          disabled={!currentRow}
          onClick={() => {}}
        >
          Delete
        </ColorButton>
        <ColorButton
          variant="contained"
          className={classes.margin}
          disabled={!currentRow}
          onClick={() => {}}
        >
          Predict
        </ColorButton>
      </div>
        </div>
      )}
      {currentTab === 1 && (
        <div>
          <h3>ADD DATA</h3>
          <Button
            variant="contained"
            color="primary"
            onClick={handleOpenDialog}
          >
            Add Data
          </Button>
          <Dialog open={openDialog} onClose={handleCloseDialog}>
            <DialogTitle>Add Data</DialogTitle>
            <DialogContent>
              <Grid container spacing={2}>
                <Grid item xs={12}>
                  <TextField
                  id="Sl_No"
                  label="SL_NO"
                  fullWidth
                  onChange={handleInputChange}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    id="customerOrderId"
                    label="Customer Order ID"
                    fullWidth
                    onChange={handleInputChange}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField id="salesOrg" label="Sales Org" fullWidth />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    id="distrChannel"
                    label="Distribution Channel"
                    fullWidth
                    onChange={handleInputChange}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    id="customerNumber"
                    label="Customer Number"
                    fullWidth
                    onChange={handleInputChange}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField id="companyCode" label="Company Code" fullWidth onChange={handleInputChange}/>
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    id="orderCurrency"
                    label="Order Currency"
                    fullWidth
                    onChange={handleInputChange}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField id="amountUSD" label="Amount in USD" fullWidth onChange={handleInputChange}/>
                </Grid>
                <Grid item xs={12}>
                  <TextField id="orderAmt" label="Order Amount" fullWidth onChange={handleInputChange}/>
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    id="orderCreationDate"
                    label="Order Creation Date"
                    type="date"
                    onChange={handleInputChange}
                    InputProps={{
                      style: { fontSize: 16, padding: "10px 12px" },
                    }}
                    InputLabelProps={{
                      shrink: true,
                    }}
                    renderInput={(params) => <TextField {...params} />}
                  />
                </Grid>
              </Grid>
            </DialogContent>
            <DialogActions>
              <Button onClick={handleCloseDialog} color="secondary">
                Cancel
              </Button>
              <Button onClick={handleAddData} color="primary">
                Add
              </Button>
            </DialogActions>
          </Dialog>
        </div>
      )}
      {currentTab === 2 && (
        <div>
          {/* Content for ANALYTICS VIEW tab */}
          <h1>ANALYTICS VIEW</h1>
          {/* Add your content here */}
        </div>
      )}
      
    </div>
  );
}
