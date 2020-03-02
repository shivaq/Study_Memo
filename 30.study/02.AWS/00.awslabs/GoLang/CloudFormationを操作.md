# AWS GoFormation

![Version](https://img.shields.io/github/v/release/awslabs/goformation?label=stable%20version) ![Commits since release](https://img.shields.io/github/commits-since/awslabs/goformation/latest) [![Actions Status](https://github.com/awslabs/goformation/workflows/Release/badge.svg)](https://github.com/awslabs/goformation/actions)
 [![GoDoc Reference](https://godoc.org/gopkg.in/awslabs/goformation.v1?status.svg)](http://godoc.org/github.com/awslabs/goformation) ![Apache-2.0](https://img.shields.io/badge/Licence-Apache%202.0-blue.svg) ![Downloads](https://img.shields.io/github/downloads/awslabs/goformation/total)

https://github.com/awslabs/goformation

`GoFormation` is a Go library for working with AWS CloudFormation / AWS Serverless Application Model (SAM) templates.

## Main features

 * Describe AWS CloudFormation and AWS SAM templates as Go objects (structs), and then turn it into JSON/YAML.
 * Parse JSON/YAML AWS CloudFormation and AWS SAM templates and turn them into Go structs.
 * Strongly typed Go structs generated for every AWS CloudFormation and AWS SAM resource.
 * Automatically generated, from the published AWS CloudFormation Resource Specification.
