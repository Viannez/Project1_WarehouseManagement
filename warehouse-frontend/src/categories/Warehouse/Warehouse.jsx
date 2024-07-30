import { useState } from "react";
import { useRef } from "react";
import { Card, CardHeader, CardMedia, CardBody, CardFooter, Link } from '@trussworks/react-uswds';

export const Warehouse = ({id, name, address, capacity}) => {
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
                    <h3 className="usa-card__heading">{name}</h3>
                </CardHeader>
                {/* <CardMedia className="flex-align-center" imageClass="circle-card margin-x-auto">
                    <img src={sparklerImage} alt="" />
                </CardMedia> */}
                <CardBody>
                    <tr>
                        <td > {address} </td>
                        <td > {capacity} </td>
                    </tr>
                </CardBody>
                {/* <CardFooter>
                    <Link href={'#'} variant="unstyled" allowSpacebarActivation className="usa-button usa-button--outline">
                    Give me good-dayssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
                    </Link>
                </CardFooter> */}
            </Card>
        </>

    )
    
}
