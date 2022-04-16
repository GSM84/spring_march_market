package ru.geekbrains.march.market.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.services.ProductService;
import ru.geekbrains.march.market.soap.products.GetAllProductsRequest;
import ru.geekbrains.march.market.soap.products.GetAllProductsResponse;
import ru.geekbrains.march.market.soap.products.GetProductByIdRequest;
import ru.geekbrains.march.market.soap.products.GetProductByIdResponse;

import java.util.Optional;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.gsm84.com/spring/ws/products";
    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        System.out.println("in controller");
        GetProductByIdResponse response = new GetProductByIdResponse();
        Optional<Product> product = productService.findById(request.getId());

        if (product.isPresent()) {
            ru.geekbrains.march.market.soap.products.Product wsProduct = new ru.geekbrains.march.market.soap.products.Product();
            wsProduct.setId(product.get().getId());
            wsProduct.setTitle(product.get().getTitle());
            wsProduct.setPrice(product.get().getPrice());
            wsProduct.setCategoryTitle(product.get().getCategory().getTitle());
            response.setProduct(wsProduct);
        }

        return response;
    }

    /*
        Пример запроса: POST http://localhost:8189/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.gsm84.com/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:GetAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        System.out.println("in controller");
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.findAll().forEach(
                p -> {
                    ru.geekbrains.march.market.soap.products.Product wsProduct = new ru.geekbrains.march.market.soap.products.Product();
                    wsProduct.setId(p.getId());
                    wsProduct.setTitle(p.getTitle());
                    wsProduct.setPrice(p.getPrice());
                    wsProduct.setCategoryTitle(p.getCategory().getTitle());
                    response.getProducts().add(wsProduct);
                });

        return response;
    }
}
