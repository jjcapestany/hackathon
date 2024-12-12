import {
    Box,
    Button,
    FormControl,
    InputLabel,
    MenuItem,
    OutlinedInput,
    Select,
    Stack,
    TextField,
    Typography
} from "@mui/material";
import {City} from "../city/CityClient.ts";
import {NumberInput} from "@mui/base/Unstable_NumberInput/NumberInput";

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
            <Box>
                <FormControl fullWidth>
                <InputLabel>Transfer from:</InputLabel>
                <Select
                    fullWidth
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
            </Box>
            <Box>
                <InputLabel
                    id="aid-type-helper">Aid:</InputLabel>
                <Select
                    fullWidth
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
            </Box>
            <Box>
                <FormControl fullWidth>
                    <InputLabel>Transport</InputLabel>
                    <Select
                        fullWidth
                        id="transport-type"
                        label="Transport"
                    >
                        <MenuItem
                            key={'Air'}
                            value={'Air'}>
                            Air
                        </MenuItem>
                        <MenuItem
                            key={'Land'}
                            value={'Land'}>
                            Land
                        </MenuItem>
                        <MenuItem
                            key={'Sea'}
                            value={'Sea'}>
                            Sea
                        </MenuItem>
                    </Select>
                </FormControl>
            </Box>
            <Box>
                <TextField label={'Amount to Transfer'} type={"number"} fullWidth>
                </TextField>
            </Box>
            <Button
                variant={'contained'}
                color={'warning'}
            >
                Start Transfer
            </Button>
        </Stack>
    )
}
export default TransferForm;