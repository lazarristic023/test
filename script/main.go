package main

import (
	"crypto/tls"
	"fmt"
	"net/http"
	"net/url"
	"sync"
	"time"
)

func sendBasicRequest(clientID, packageType, urlStr string, wg *sync.WaitGroup, commercialId string) {
	defer wg.Done()

	params := url.Values{}
	params.Add("commercialId", commercialId)
	params.Add("clientId", clientID)
	params.Add("packageType", packageType)
	reqURL := fmt.Sprintf("%s?%s", urlStr, params.Encode())

	tr := &http.Transport{
		TLSClientConfig: &tls.Config{InsecureSkipVerify: true}, // InsecureSkipVerify true za testne svrhe, preporučuje se promena u produkciji
	}

	client := &http.Client{
		Transport: tr,
	}

	resp, err := client.Get(reqURL)
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	defer resp.Body.Close()

	var body []byte
	resp.Body.Read(body)
	fmt.Println(string(body))
}

func sendStandardRequest(clientID, packageType, urlStr string, wg *sync.WaitGroup, commercialId string) {
	defer wg.Done()

	params := url.Values{}
	params.Add("commercialId", commercialId)
	params.Add("clientId", clientID)
	params.Add("packageType", packageType)
	reqURL := fmt.Sprintf("%s?%s", urlStr, params.Encode())

	tr := &http.Transport{
		TLSClientConfig: &tls.Config{InsecureSkipVerify: true}, // InsecureSkipVerify true za testne svrhe, preporučuje se promena u produkciji
	}

	client := &http.Client{
		Transport: tr,
	}

	resp, err := client.Get(reqURL)
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	defer resp.Body.Close()

	var body []byte
	resp.Body.Read(body)
	fmt.Println(string(body))
}
func sendGoldRequest(clientID, packageType, urlStr string, wg *sync.WaitGroup, commercialId string) {
	defer wg.Done()

	params := url.Values{}
	params.Add("commercialId", commercialId)
	params.Add("clientId", clientID)
	params.Add("packageType", packageType)
	reqURL := fmt.Sprintf("%s?%s", urlStr, params.Encode())

	tr := &http.Transport{
		TLSClientConfig: &tls.Config{InsecureSkipVerify: true}, // InsecureSkipVerify true za testne svrhe, preporučuje se promena u produkciji
	}

	client := &http.Client{
		Transport: tr,
	}

	resp, err := client.Get(reqURL)
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	defer resp.Body.Close()

	var body []byte
	resp.Body.Read(body)
	fmt.Println(string(body))
}

func main() {
	clientID := "1"
	commercialId := "1"
	packageBasicType := "basic"
	packageStandardType := "standard"
	//packageGoldType := "gold"
	urlStr := "https://localhost:8081/api/commercial/commercial-click"
	numBasicRequests := 11
	numStandardRequests := 101
	//numGoldRequests := 10001
	var wg sync.WaitGroup
	wg.Add(numBasicRequests)

	for i := 0; i < numBasicRequests; i++ {
		go sendBasicRequest(clientID, packageBasicType, urlStr, &wg, commercialId)
		time.Sleep(100 * time.Millisecond) // Delay between requests to avoid instant burst
	}

	wg.Wait()

	wg.Add(numStandardRequests)

	for i := 0; i < numStandardRequests; i++ {
		go sendStandardRequest(clientID, packageStandardType, urlStr, &wg, commercialId)
		time.Sleep(100 * time.Millisecond) // Delay between requests to avoid instant burst
	}

	/*
		wg.Wait()

		wg.Add(numGoldRequests)

		for i := 0; i < numGoldRequests; i++ {
			go sendGoldRequest(clientID, packageGoldType, urlStr, &wg, commercialId)
			time.Sleep(100 * time.Millisecond) // Delay between requests to avoid instant burst
		}

		wg.Wait()
	*/
}
