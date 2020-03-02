https://github.com/awslabs/amazon-virtual-andon

# Amazon Virtual ANDON Solution
Amazon Virtual Andon is a self-service, cloud based andon system that makes it easy for any business to deploy andon in their factory. It is based on the same technology used by the Amazon Fulfillment centers built on AWS.

The events occurring on the factory floor are captured either using a web-interface or connecting the machines to AWS IoT core that publish the events to a topic. These events are then stored in Dynamo DB. Using the IoT Rule Engine, the events are integrated with other AWS services such as SNS to send notifications about the events and Amazon Sagemaker for machine learning.

The solution comes with 4 different user personas, Admin, Manager, Engineer and Associate.

For more information and a detailed deployment guide visit the Amazon Virtual Andon solution at https://aws.amazon.com/solutions/amazon-virtual-andon/.
