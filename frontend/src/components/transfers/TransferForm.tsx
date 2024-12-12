import {Button, FormControl, InputLabel, MenuItem, Select, Stack, Typography} from "@mui/material";
import {City} from "../city/CityClient.ts";

type TransferFormProps = {
  cities: City[]
  selectedCity: City
}

const TransferForm = ({cities, selectedCity}: TransferFormProps) => {
  return (
    <Stack sx={{position: 'absolute', top: 40, left: 60, width: 275}} flexDirection={'column'} gap={1}>
      <Typography>
        {`Transfer aid to ${selectedCity.name}`}
      </Typography>
      <FormControl>
        <InputLabel
          id="transfer-from-helper">Transfer from:</InputLabel>
        <Select
          labelId="transfer-from-helper"
          id="transfer-from"
          label="Transfer from:"
        >
          {cities.filter((city) => city.name != selectedCity.name).map((city) => (
            <MenuItem
              key={city.name}
              value={city.name}
            >
              {city.name}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      <FormControl>
        <InputLabel
          id="aid-type-helper">Aid:</InputLabel>
        <Select
          labelId="aid-type-helper"
          id="aid-type"
          label="Transfer from:"
        >
          <MenuItem
            key={'Water'}
            value={'Water'}
          >
            Water
          </MenuItem>
          <MenuItem
            key={'Food'}
            value={'Food'}
          >
            Food
          </MenuItem>
          <MenuItem
            key={'Medical'}
            value={'Medical'}
          >
            Medical
          </MenuItem>
        </Select>
        <Button
          variant={'contained'}
          color={'warning'}
        >
          Start Transfer
        </Button>
      </FormControl>
    </Stack>
  )
}
export default TransferForm;