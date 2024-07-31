import { useState } from "react";
import { useRef } from "react";
import { Card, CardHeader, CardMedia, CardBody, CardFooter, Link, Button} from '@trussworks/react-uswds';

import './Warehouse.css'

export const Warehouse = ({warehouse}) => {
    return (
        <>
            <Card layout="flagMediaRight" gridLayout={{
            tablet: {
            col: 3
            }
            }} containerProps={{
                className: 'border-primary-vivid'
            }}>
                <CardHeader>
                    <h3 className="usa-card__heading">{warehouse.name}</h3>
                </CardHeader>
                {/* <CardMedia className="flex-align-center" imageClass="circle-card margin-x-auto">
                    <img src={sparklerImage} alt="" />
                </CardMedia> */}
                <CardBody>
                    <table> 
                        <tbody>
                            <tr>
                                <td > {warehouse.address} </td>
                                <td > {warehouse.inventoryCapacity}/{warehouse.capacity} </td>
                                <td>
                                <Link className="usa-button" variant="unstyled" allowSpacebarActivation href={'#'}>
                                    Open Warehouse 
                                </Link>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </CardBody>
                {/* <CardFooter>
                    <Link href={'#'} variant="unstyled" allowSpacebarActivation className="usa-button usa-button--outline">
                    Give me good-days
                    </Link>
                </CardFooter> */}
            </Card>
        </>
    )
}
