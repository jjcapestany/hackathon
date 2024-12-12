import {Box, Button, FormControl, InputLabel, MenuItem, Select, Stack, TextField, Typography} from "@mui/material";
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
                <FormControl fullWidth>
                <InputLabel>Aid</InputLabel>
                <Select
                    fullWidth
                    label="Aid"
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
                </FormControl>
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
            <Typography textAlign={'center'}>Estimated time of supply arrival:</Typography>
            <Typography textAlign={'center'}>1 day</Typography>
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